package br.com.syshealth.gestao.bradesco.core.handler.cadastro;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.syshealth.commons.dto.Cadastro;
import br.com.syshealth.commons.dto.Empresa;
import br.com.syshealth.commons.dto.Plano;
import br.com.syshealth.commons.dto.Segurado;
import br.com.syshealth.commons.dto.SubEmpresa;
import br.com.syshealth.commons.enums.OperadoraEnum;
import br.com.syshealth.commons.utils.StringUtils;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.FixedFormat;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.CadastroDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.DadosBancariosDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.DependenteDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.TitularComplementoDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.TitularDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.TraillerDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.CoreValidationException;
import br.com.syshealth.gestao.bradesco.commons.interfaces.GestaoBradescoDePara;
import br.com.syshealth.gestao.bradesco.core.port.inbound.GestaoBradescoListenerInputService;
import br.com.syshealth.gestao.bradesco.core.port.outbound.GestaoBradescoOutboundDbService;
import br.com.syshealth.gestao.bradesco.core.port.outbound.JmsClient;

@Service
public class GestaoBradescoCadastroServiceHandler implements GestaoBradescoListenerInputService {

    private static final int TOTAL_COLUNA_ARQUIVO = 180;

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(GestaoBradescoCadastroServiceHandler.class);

    /** The outbound. */
    @Autowired
    private GestaoBradescoOutboundDbService outbound;

    /** The outbound. */
    @Autowired
    private JmsClient syshealth;

    /** The validador. */
    @Autowired
    GestaoBradescoCadastroValidator gestaoBradescoValidator;

    private Scanner entrada;

    @Override
    public void processaMensagem(String msg) throws CoreValidationException {
        log.info("inicio processaMensagem");

        FixedFormat ff = new FixedFormat();
        ff.setHeaderSize(TOTAL_COLUNA_ARQUIVO);
        ff.setTitularSize(TOTAL_COLUNA_ARQUIVO);
        ff.setTitularComplementoSize(TOTAL_COLUNA_ARQUIVO);
        ff.setDadosBancariosSize(TOTAL_COLUNA_ARQUIVO);
        ff.setDependenteSize(TOTAL_COLUNA_ARQUIVO);
        ff.setTraillerSize(TOTAL_COLUNA_ARQUIVO);

        CadastroDto cadastro = null;
        TitularDto titular = null;
        entrada = new Scanner(msg);

        Integer totalTitulares = 0;
        Integer totalDependentes = 0;
        Integer totalDadosBancarios = 0;
        while (entrada.hasNext()) {
            String linha = entrada.nextLine();

            switch (linha.substring(0, 1)) {
            case "1":
                if (cadastro == null) {
                    cadastro = new CadastroDto();
                    cadastro = ff.readLines(linha, CadastroDto.class);
                } else {
                    CadastroDto cadastroErro = ff.readLines(linha, CadastroDto.class);
                    throw new IllegalArgumentException("Header da " + cadastro.getNumeroDoContrato()
                            + " inesperado dentro do mesmo arquivo " + cadastroErro.toString() + "!");
                }
                break;
            case "2":
                if (cadastro == null) {
                    throw new IllegalArgumentException("Header da cadastro não foi localizado!");
                }

                titular = new TitularDto();
                titular = ff.readLines(linha, TitularDto.class);
                cadastro.getTitular().add(titular);
                totalTitulares++;
                break;
            case "5":
                if (titular != null)
                    titular.setTitularComplemento(ff.readLines(linha, TitularComplementoDto.class));
                else
                    throw new IllegalArgumentException("Não existe titular para adicionar os complemento!");
                break;
            case "9":
                if (titular != null)
                    titular.setDadosBancarios(ff.readLines(linha, DadosBancariosDto.class));
                else
                    throw new IllegalArgumentException("Não existe titular para adicionar os dados bancarios!");
                totalDadosBancarios++;
                break;
            case "3":
                if (titular != null) {
                    titular.getDependente().add(ff.readLines(linha, DependenteDto.class));
                } else
                    throw new IllegalArgumentException(
                            "Não existe titular para adicionar os dependentes!");
                totalDependentes++;
                break;
            case "4":
                if (cadastro != null) {
                    cadastro.setTrailler(ff.readLines(linha, TraillerDto.class));
                } else {
                    throw new IllegalArgumentException("Não existe cadastro!");
                }
                break;
            default:
                break;
            }
        }

        gestaoBradescoValidator.validar(cadastro, totalTitulares, totalDependentes, totalDadosBancarios);

        enviarSysHealth(cadastro);

        outbound.inserir(cadastro);
    }

    private void enviarSysHealth(CadastroDto cadastro) {
        log.info("enviarSysHealth: {}", cadastro);
        try {
            montarSysHealth(cadastro);
        } catch (CoreValidationException e) {
            log.error("montarSysHealth {}", e);
        }
        log.info("montarSysHealth finalizado!");
    }

    private void montarSysHealth(CadastroDto cadastro) throws CoreValidationException {
        log.info("montarSysHealth: {}", cadastro);

        for (TitularDto titular : cadastro.getTitular()) {

            Empresa empresa = new Empresa(null, null, cadastro.getNumeroDoContrato(),
                    OperadoraEnum.BRADESCO);

            SubEmpresa subEmpresa = new SubEmpresa(titular.getNumeroDaSubfatura(), null, null, null);

            Segurado seguradoTitular = new Segurado(new Long(titular.getNumeroDoCertificado().toString() + "00"),
                    titular.getTitularComplemento().getNumeroCartao(),
                    titular.getNomeDoSegurado(),
                    titular.getCpf(),
                    titular.getDataDeNascimento(),
                    titular.getDataDeInicioDeVigencia(),
                    null,
                    GestaoBradescoDePara.retornaTipoBeneficiario(0),
                    GestaoBradescoDePara.retornaSexo(titular.getSexoDoSegurado()),
                    null,
                    GestaoBradescoDePara
                            .retonaEstadoCivil(titular.getEstadoCivil()),
                    StringUtils.calculaIdade(titular.getDataDeNascimento()),
                    new Plano(titular.getPlano(),
                            titular.getPlano()));

            syshealth.sendCadastro(new Cadastro(cadastro.getDataDeCompetencia(), empresa, subEmpresa, seguradoTitular));

            for (DependenteDto dependente : titular.getDependente()) {

                Segurado seguradoDependente = new Segurado(new Long(dependente.getNumeroDoCertificado().toString()
                        + StringUtils.lpad(dependente.getCodigoDoDependente().toString(), "0", 2)),
                        dependente.getNumeroDoCartao(),
                        dependente.getNomeDoDependente(),
                        dependente.getCpfDependente(),
                        dependente.getDataDeNascimento(),
                        dependente.getDataDeInicioDeVigencia(),
                        null,
                        GestaoBradescoDePara.retornaTipoBeneficiario(dependente.getCodigoDoDependente()),
                        GestaoBradescoDePara.retornaSexo(dependente.getSexoDoDependente()),
                        GestaoBradescoDePara.retornaParentesco(dependente.getGrauDeParentesco()),
                        GestaoBradescoDePara.retonaEstadoCivil(dependente.getEstadoCivilDoDependente()),
                        StringUtils.calculaIdade(dependente.getDataDeNascimento()),
                        seguradoTitular.getPlano());

                syshealth.sendCadastro(new Cadastro(cadastro.getDataDeCompetencia(), empresa, subEmpresa, seguradoDependente));
            }
        }
    }

}
