package br.com.syshealth.gestao.bradesco.core.handler.fatura;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.syshealth.commons.dto.EmpresaPremio;
import br.com.syshealth.commons.dto.Plano;
import br.com.syshealth.commons.dto.Premio;
import br.com.syshealth.commons.dto.SeguradoPremio;
import br.com.syshealth.commons.dto.SubEmpresaPremio;
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
            syshealth.sendPremio(montarSysHealth(premio));
        } catch (CoreValidationException e) {
            log.error("montarSysHealth {}", e);
        }
        log.info("montarSysHealth finalizado!");
    }

    private Premio montarSysHealth(PremioDto fatura) {
        log.info("montarSysHealth: {}", fatura);

        List<SubEmpresaPremio> subEmpresas = new ArrayList<>();
        for (HeaderSubFaturaDto headerSubFaturaDto : fatura.getHeaderSubFatura()) {

            List<SeguradoPremio> segurados = new ArrayList<>();
            for (TitularDependenteDto titularDependenteDto : headerSubFaturaDto.getTitularDependente()) {

                TipoBeneficiarioEnum tipoBeneficiario = null;
                SexoEnum sexo = null;
                GrauParentescoEnum parentesco = null;
                EstadoCivilEnum estadoCivil = null;
                SeguradoPremio segurado = null;
                Integer idade = null;
                Plano plano = null;

                Long codigoSegurado = new Long(titularDependenteDto.getNumeroDoCertificado().toString()
                        + StringUtils.lpad(titularDependenteDto.getComplementoDoCertificado().toString(), "0", 2));

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

                    segurado = criaSegurado(titularDependenteDto,
                            tipoBeneficiario,
                            sexo,
                            parentesco,
                            estadoCivil,
                            idade,
                            plano,
                            codigoSegurado);

                } else // Cobrança Diferenciada
                    segurado = criaSegurado(titularDependenteDto,
                            tipoBeneficiario,
                            sexo,
                            parentesco,
                            estadoCivil,
                            idade,
                            plano,
                            codigoSegurado);

                segurados.add(segurado);

            }

            SubEmpresaPremio subEmpresa = new SubEmpresaPremio(headerSubFaturaDto.getNumeroDaSubfatura(),
                    headerSubFaturaDto.getNomeDaSubfatura(),
                    segurados, null, null);

            subEmpresas.add(subEmpresa);
        }

        EmpresaPremio empresa = new EmpresaPremio(fatura.getCodigoDaCompanhia(),
                fatura.getNomeDoEstipulante(),
                fatura.getCodigoDoContrato(),
                OperadoraEnum.BRADESCO,
                subEmpresas);

        return new Premio(fatura.getCompetenciaDaFatura(), empresa);
    }

    private SeguradoPremio criaSegurado(TitularDependenteDto titularDependenteDto, TipoBeneficiarioEnum tipoBeneficiario, SexoEnum sexo,
            GrauParentescoEnum parentesco, EstadoCivilEnum estadoCivil, Integer idade, Plano plano, Long codigoSegurado) {

        return new SeguradoPremio(codigoSegurado, null,
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
                plano,
                BigDecimal.valueOf(titularDependenteDto.getValorDoLancamento()),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.valueOf(titularDependenteDto.getParteDoSegurado()));
    }
}
