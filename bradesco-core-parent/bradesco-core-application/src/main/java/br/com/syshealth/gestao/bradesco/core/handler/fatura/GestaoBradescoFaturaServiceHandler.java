package br.com.syshealth.gestao.bradesco.core.handler.fatura;

import java.math.BigDecimal;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.syshealth.commons.dto.Empresa;
import br.com.syshealth.commons.dto.Plano;
import br.com.syshealth.commons.dto.Premio;
import br.com.syshealth.commons.dto.Segurado;
import br.com.syshealth.commons.dto.SubEmpresa;
import br.com.syshealth.commons.enums.EstadoCivilEnum;
import br.com.syshealth.commons.enums.GrauParentescoEnum;
import br.com.syshealth.commons.enums.OperadoraEnum;
import br.com.syshealth.commons.enums.SexoEnum;
import br.com.syshealth.commons.enums.TipoBeneficiarioEnum;
import br.com.syshealth.commons.utils.StringUtils;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.CoreValidationException;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.FixedFormat;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.HeaderSubFaturaDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.PremioDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.TitularDependenteDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.TraillerDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.TraillerObservacaoDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.TraillerSubFaturaDto;
import br.com.syshealth.gestao.bradesco.commons.interfaces.GestaoBradescoDePara;
import br.com.syshealth.gestao.bradesco.core.port.inbound.GestaoBradescoListenerInputService;
import br.com.syshealth.gestao.bradesco.core.port.outbound.GestaoBradescoOutboundDbService;
import br.com.syshealth.gestao.bradesco.core.port.outbound.JmsClient;

@Service
public class GestaoBradescoFaturaServiceHandler implements GestaoBradescoListenerInputService {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(GestaoBradescoFaturaServiceHandler.class);

    /** The outbound. */
    @Autowired
    private GestaoBradescoOutboundDbService outbound;

    /** The outbound. */
    @Autowired
    private JmsClient syshealth;

    /** The validador. */
    @Autowired
    GestaoBradescoFaturaValidator gestaoBradescoValidator;

    private Scanner entrada;

    @Override
    public void processaMensagem(String msg) throws CoreValidationException {
        log.info("inicio processaMensagem");

        FixedFormat ff = new FixedFormat();
        ff.setHeaderSize(180);
        ff.setHeaderSubFaturaSize(180);
        ff.setTitularDependenteSize(180);
        ff.setTraillerSubFaturaSize(180);
        ff.setTraillerObservacaoSize(180);
        ff.setTraillerSize(180);

        PremioDto fatura = null;
        HeaderSubFaturaDto subFatura = null;

        entrada = new Scanner(msg);
        while (entrada.hasNext()) {
            String linha = entrada.nextLine();

            switch (linha.substring(0, 1)) {
            case "1":
                if (fatura == null) {
                    fatura = new PremioDto();
                    fatura = ff.readLines(linha, PremioDto.class);
                } else {
                    PremioDto faturaErro = ff.readLines(linha, PremioDto.class);
                    throw new CoreValidationException("Header da " + fatura.getNomeDoEstipulante()
                            + " inesperado dentro do mesmo arquivo " + faturaErro.toString() + "!");
                }
                break;
            case "2":
                if (fatura == null) {
                    throw new CoreValidationException("Header da fatura não foi localizado!");

                }
                if (subFatura == null || subFatura.getTraillerSubFatura() != null) {
                    subFatura = new HeaderSubFaturaDto();
                    subFatura = ff.readLines(linha, HeaderSubFaturaDto.class);
                    fatura.getHeaderSubFatura().add(subFatura);
                } else {
                    throw new CoreValidationException("Sub Fatura " + subFatura.getNumeroDaSubfatura()
                            + " não encerrada adequadamente, faltou o trailler da subFatura!");
                }
                break;
            case "3":
                if (subFatura != null)
                    subFatura.getTitularDependente().add(ff.readLines(linha, TitularDependenteDto.class));
                else
                    throw new CoreValidationException("Não existe subFatura para adicionar as faturas!");
                break;
            case "4":
                if (subFatura != null) {
                    TraillerSubFaturaDto traillerSubFatura;
                    TraillerObservacaoDto traillerObersevacao;

                    if ("(".equals(linha.substring(5, 6).trim())) {
                        traillerSubFatura = ff.readLines(linha, TraillerSubFaturaDto.class);
                        if (traillerSubFatura.getNumeroDaSubfatura() == 9999)
                            fatura.getTraillerFatura().add(traillerSubFatura);
                        else
                            subFatura.getTraillerSubFatura().add(traillerSubFatura);
                    } else {
                        traillerObersevacao = ff.readLines(linha, TraillerObservacaoDto.class);
                        if (traillerObersevacao.getNumeroSubFatura() == 9999)
                            fatura.getTraillerObservacao().add(traillerObersevacao);
                        else
                            subFatura.getTraillerObservacao().add(traillerObersevacao);
                    }

                } else
                    throw new CoreValidationException("Não existe subFatura para adicionar o trailler da subfatura!");

                break;
            case "5":
                if (fatura != null) {
                    fatura.setTrailler(ff.readLines(linha, TraillerDto.class));
                } else {
                    throw new CoreValidationException("Não existe Fatura!");
                }
                break;
            default:
                break;
            }
        }

        gestaoBradescoValidator.validar(fatura);

        enviarSysHealth(fatura);

        outbound.inserir(fatura);
    }

    private void enviarSysHealth(PremioDto premio) {
        log.info("enviarSysHealth: {}", premio);
        try {
            montarSysHealth(premio);
        } catch (CoreValidationException e) {
            log.error("montarSysHealth {}", e);
        }
        log.info("montarSysHealth finalizado!");
    }

    private void montarSysHealth(PremioDto fatura) throws CoreValidationException {
        log.info("montarSysHealth: {}", fatura);

        Empresa empresa = new Empresa(fatura.getCodigoDaCompanhia(),
                fatura.getNomeDoEstipulante(),
                fatura.getCodigoDoContrato(),
                OperadoraEnum.BRADESCO);

        for (HeaderSubFaturaDto headerSubFaturaDto : fatura.getHeaderSubFatura()) {

            SubEmpresa subEmpresa = new SubEmpresa(headerSubFaturaDto.getNumeroDaSubfatura(),
                    headerSubFaturaDto.getNomeDaSubfatura(), null, null);

            for (TitularDependenteDto titularDependenteDto : headerSubFaturaDto.getTitularDependente()) {

                TipoBeneficiarioEnum tipoBeneficiario = null;
                SexoEnum sexo = null;
                GrauParentescoEnum parentesco = null;
                EstadoCivilEnum estadoCivil = null;
                Segurado segurado = null;
                Integer idade = null;
                Plano plano = null;

                Long codigoSegurado = GestaoBradescoDePara.retornaCodigoSegurado(titularDependenteDto.getNumeroDoCertificado(),
                        titularDependenteDto.getComplementoDoCertificado());

                /** Igual a Segurado **/
                if (codigoSegurado != 0) {
                    tipoBeneficiario = GestaoBradescoDePara
                            .retornaTipoBeneficiario(titularDependenteDto.getComplementoDoCertificado());

                    sexo = GestaoBradescoDePara.retornaSexo(titularDependenteDto.getCodigoSexo());

                    /** Diferente de titular **/
                    if (titularDependenteDto.getCodGrauParentDep() != 0)
                        parentesco = GestaoBradescoDePara
                                .retornaParentesco(titularDependenteDto.getCodGrauParentDep());

                    estadoCivil = GestaoBradescoDePara
                            .retonaEstadoCivil(titularDependenteDto.getCodigoEstCivil());

                    plano = new Plano(titularDependenteDto.getCodigoDoPlano(),
                            titularDependenteDto.getCodigoDoPlano());

                    idade = StringUtils.calculaIdade(titularDependenteDto.getDataDeNascimento());

                    segurado = new Segurado(codigoSegurado, null,
                            titularDependenteDto.getNomeDoSeguradoDependente(),
                            null,
                            titularDependenteDto.getDataDeNascimento(),
                            titularDependenteDto.getDataInicioVigencia(),
                            null,
                            tipoBeneficiario,
                            sexo,
                            parentesco,
                            estadoCivil,
                            idade,
                            plano);

                } else // Cobrança Diferenciada
                    segurado = new Segurado(codigoSegurado, null,
                            titularDependenteDto.getNomeDoSeguradoDependente(),
                            null,
                            titularDependenteDto.getDataDeNascimento(),
                            titularDependenteDto.getDataInicioVigencia(),
                            null,
                            tipoBeneficiario,
                            sexo,
                            parentesco,
                            estadoCivil,
                            idade,
                            plano);

                syshealth.sendPremio(
                        new Premio(fatura.getCompetenciaDaFatura(), empresa, subEmpresa, segurado,
                                BigDecimal.valueOf(titularDependenteDto.getValorDoLancamento()),
                                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(titularDependenteDto.getParteDoSegurado())));
            }
        }
    }
}
