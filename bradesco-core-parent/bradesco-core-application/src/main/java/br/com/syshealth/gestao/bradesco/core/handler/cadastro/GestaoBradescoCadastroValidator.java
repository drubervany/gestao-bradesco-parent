package br.com.syshealth.gestao.bradesco.core.handler.cadastro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.CadastroDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.CoreValidationException;

/**
 * The Class IdentificadorValidator.
 * 
 * @author Danilo.Rubervany
 */
@Service
public class GestaoBradescoCadastroValidator {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(GestaoBradescoCadastroValidator.class);

    /**
     * Instantiates a new identificador validator.
     */
    public GestaoBradescoCadastroValidator() {

    }

    /**
     * Validar.
     * 
     * @param fatura
     * @throws CoreValidationException
     *             the core validation exception
     */
    public void validar(CadastroDto cadastro, Integer totalTitulares, Integer totalDependentes, Integer totalDadosBancarios)
            throws CoreValidationException {
        log.info("inicio validar");

        if (cadastro.getTrailler().getTotalDeTitulares().intValue() != totalTitulares.intValue()) {
            throw new IllegalArgumentException(
                    "Total de registros Titulares diferentes Trailler " + cadastro.getTrailler().getTotalDeTitulares()
                            + " <> Registros "
                            + totalTitulares);
        }

        if (cadastro.getTrailler().getTotalDeDependentes().intValue() != totalTitulares.intValue()) {
            throw new IllegalArgumentException(
                    "Total de registros Dependentes diferentes Trailler " + cadastro.getTrailler().getTotalDeTitulares()
                            + " <> Registros "
                            + totalDependentes);
        }

        Integer TotalRegistros = totalTitulares + totalDependentes + totalDadosBancarios;
        if (cadastro.getTrailler().getTotalDeRegistros().intValue() != Integer.valueOf(totalTitulares - 1).intValue()) {
            throw new IllegalArgumentException(
                    "Total de registros diferentes Trailler " + cadastro.getTrailler().getTotalDeRegistros() + " <> Registros "
                            + TotalRegistros);
        }

        log.info("Fim validar");
    }
}
