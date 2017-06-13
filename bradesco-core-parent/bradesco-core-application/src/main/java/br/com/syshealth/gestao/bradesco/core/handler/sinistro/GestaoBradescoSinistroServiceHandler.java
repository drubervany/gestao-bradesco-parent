package br.com.syshealth.gestao.bradesco.core.handler.sinistro;

import java.math.BigDecimal;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.syshealth.commons.dto.Empresa;
import br.com.syshealth.commons.dto.Plano;
import br.com.syshealth.commons.dto.Prestador;
import br.com.syshealth.commons.dto.Procedimento;
import br.com.syshealth.commons.dto.Segurado;
import br.com.syshealth.commons.dto.Sinistro;
import br.com.syshealth.commons.dto.SubEmpresa;
import br.com.syshealth.commons.enums.EstadoCivilEnum;
import br.com.syshealth.commons.enums.GrauParentescoEnum;
import br.com.syshealth.commons.enums.OperadoraEnum;
import br.com.syshealth.commons.enums.RedeReembolsoEnum;
import br.com.syshealth.commons.enums.SexoEnum;
import br.com.syshealth.commons.enums.SimNaoEnum;
import br.com.syshealth.commons.enums.TipoBeneficiarioEnum;
import br.com.syshealth.commons.utils.StringUtils;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.CoreValidationException;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.FixedFormat;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.MovimentoDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.SinistroDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.TraillerDto;
import br.com.syshealth.gestao.bradesco.commons.interfaces.GestaoBradescoDePara;
import br.com.syshealth.gestao.bradesco.core.port.inbound.GestaoBradescoListenerInputService;
import br.com.syshealth.gestao.bradesco.core.port.outbound.GestaoBradescoOutboundDbService;
import br.com.syshealth.gestao.bradesco.core.port.outbound.JmsClient;

@Service
public class GestaoBradescoSinistroServiceHandler implements GestaoBradescoListenerInputService {

    private static final int TOTAL_COLUNA_ARQUIVO = 500;

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(GestaoBradescoSinistroServiceHandler.class);

    /** The outbound. */
    @Autowired
    private GestaoBradescoOutboundDbService outbound;

    /** The outbound. */
    @Autowired
    private JmsClient syshealth;

    /** The validador. */
    @Autowired
    GestaoBradescoSinistroValidator gestaoBradescoValidator;

    private Scanner entrada;

    @Override
    public void processaMensagem(String msg) throws CoreValidationException {
        log.info("inicio processaMensagem");

        FixedFormat ff = new FixedFormat();
        ff.setHeaderSize(TOTAL_COLUNA_ARQUIVO);
        ff.setMovimentoSize(TOTAL_COLUNA_ARQUIVO);
        ff.setTraillerSize(TOTAL_COLUNA_ARQUIVO);

        SinistroDto sinistro = null;
        entrada = new Scanner(msg);
        while (entrada.hasNext()) {
            String linha = entrada.nextLine();

            switch (linha.substring(0, 1)) {
            case "H":
                if (sinistro == null) {
                    sinistro = new SinistroDto();
                    sinistro = ff.readLines(linha, SinistroDto.class);
                } else {
                    SinistroDto sinistroErro = ff.readLines(linha, SinistroDto.class);
                    throw new IllegalArgumentException("Header da " + sinistro.getNumeroDaApolice()
                            + " inesperado dentro do mesmo arquivo " + sinistroErro.toString() + "!");
                }
                break;
            case "M":
                if (sinistro != null)
                    sinistro.getMovimento().add(ff.readLines(linha, MovimentoDto.class));
                else
                    throw new IllegalArgumentException("Não existe subFatura para adicionar as faturas!");
                break;

            case "T":
                if (sinistro != null) {
                    sinistro.setTrailler(ff.readLines(linha, TraillerDto.class));
                } else {
                    throw new IllegalArgumentException("Não existe Fatura!");
                }
                break;
            default:
                break;
            }
        }

        gestaoBradescoValidator.validar(sinistro);

        enviarSysHealth(sinistro);

        outbound.inserir(sinistro);
    }

    private void enviarSysHealth(SinistroDto sinistro) {
        log.info("enviarSysHealth: {}", sinistro);
        try {
            montarSysHealth(sinistro);
        } catch (CoreValidationException e) {
            log.error("montarSysHealth {}", e);
        }
        log.info("montarSysHealth finalizado!");
    }

    private void montarSysHealth(SinistroDto sinistro) throws CoreValidationException {
        log.info("montarSysHealth: {}", sinistro);

        Empresa empresa = new Empresa(sinistro.getNumeroDaApolice(),
                null,
                null,
                OperadoraEnum.BRADESCO);

        for (MovimentoDto movimento : sinistro.getMovimento()) {

            SubEmpresa subEmpresa = new SubEmpresa(movimento.getNumeroDaSubfatura(), movimento.getNomeDaSubfatura(), null, null);

            TipoBeneficiarioEnum tipoBeneficiario = null;
            SexoEnum sexo = null;
            GrauParentescoEnum parentesco = null;
            EstadoCivilEnum estadoCivil = null;
            Segurado segurado = null;
            Integer idade = null;
            Plano plano = null;
            RedeReembolsoEnum redeReembolso = null;
            SimNaoEnum internado = null;
            Procedimento procedimento = null;
            Prestador prestador = null;

            final Long codigoSegurado = GestaoBradescoDePara.retornaCodigoSegurado(movimento.getNumeroDoCertificado(),
                    movimento.getCodigoDoPaciente());

            /** Igual a Segurado **/
            if (codigoSegurado != 0) {
                tipoBeneficiario = GestaoBradescoDePara
                        .retornaTipoBeneficiario(movimento.getCodigoDoPaciente());

                sexo = GestaoBradescoDePara.retornaSexo(movimento.getSexo());

                /** Diferente de titular **/
                if (movimento.getGrauDeParentesco() != 0)
                    parentesco = GestaoBradescoDePara
                            .retornaParentesco(movimento.getGrauDeParentesco());

                // estadoCivil = GestaoBradescoDePara
                // .retonaEstadoCivil(movimento.getCodigoEstCivil());

                plano = new Plano(movimento.getPlanoDoSegurado(),
                        movimento.getPlanoDoSegurado());

                idade = StringUtils.calculaIdade(movimento.getDataDeNascimento());

                redeReembolso = GestaoBradescoDePara
                        .retornaRedeReembolso(movimento.getCpfCgcDoReferenciado());

                internado = GestaoBradescoDePara
                        .retornaInternado(movimento.getNumeroDoDocumento());

                procedimento = new Procedimento(movimento.getCodigoDoProcedimento(), null);

                prestador = new Prestador(movimento.getCodigoDoReferenciado(), movimento.getNomeDoBeneficiario(),
                        movimento.getCpfCgcDoReferenciado(), movimento.getTipoDoReferenciado());

                segurado = new Segurado(codigoSegurado,
                        null,
                        movimento.getNomeDoPaciente(), null,
                        movimento.getDataDeNascimento(),
                        movimento.getDataDeAdmissao(), null,
                        tipoBeneficiario, sexo, parentesco, estadoCivil, idade, plano);

            } else // Cobrança Diferenciada
                segurado = new Segurado(codigoSegurado,
                        null,
                        movimento.getNomeDoPaciente(), null,
                        movimento.getDataDeNascimento(),
                        movimento.getDataDeAdmissao(), null,
                        tipoBeneficiario, sexo, parentesco, estadoCivil, idade, plano);

            syshealth.sendSinistro(
                    new Sinistro(sinistro.getDataDeCompetencia(), empresa, subEmpresa, segurado, null, movimento.getDataDoEvento(),
                            movimento.getDataDoPagamento(), null, procedimento, movimento.getQuantidadeProcedimentos(), prestador,
                            BigDecimal.valueOf(movimento.getValorDoSinistro()),
                            BigDecimal.valueOf(movimento.getValorDoRecibo()),
                            BigDecimal.valueOf(movimento.getValorPago()),
                            BigDecimal.valueOf(movimento.getValorDeInssOuIssFAJTR()),
                            BigDecimal.valueOf(movimento.getValorDeInssOuIss()), movimento.getNumeroDoDocumento(), redeReembolso, internado,
                            null,
                            null));

        }

    }
}
