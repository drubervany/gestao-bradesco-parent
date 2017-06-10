package br.com.syshealth.gestao.bradesco.core.handler.sinistro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.CoreValidationException;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.MovimentoDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.SinistroDto;

/**
 * The Class IdentificadorValidator.
 * 
 * @author Danilo.Rubervany
 */
@Service
public class GestaoBradescoSinistroValidator {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(GestaoBradescoSinistroValidator.class);

    /**
     * Instantiates a new identificador validator.
     */
    public GestaoBradescoSinistroValidator() {

    }

    /**
     * Validar.
     * 
     * @param fatura
     * @throws CoreValidationException
     *             the core validation exception
     */
    public void validar(SinistroDto sinistro) throws CoreValidationException {
        log.info("inicio validar");

        Double valorPago = new Double(0);
        for (MovimentoDto movimento : sinistro.getMovimento()) {
            valorPago = valorPago + movimento.getValorPago();
        }

        log.info("Valor total " + valorPago);
        log.info("Total De ValorPago " + sinistro.getTrailler().getTotalDeValorPago());

        double tolerance = 0.000001;
        if (Math.abs(valorPago - sinistro.getTrailler().getTotalDeValorPago()) > tolerance) {
            throw new IllegalArgumentException("Total valor pago diferentes " + valorPago + " <> "
                    + sinistro.getTrailler().getTotalDeValorPago().doubleValue());
        }

        if (sinistro.getMovimento().size() != sinistro.getTrailler().getTotalDeRegistros()) {
            throw new IllegalArgumentException("Total de registros diferentes " + sinistro.getMovimento().size() + " <> "
                    + sinistro.getTrailler().getTotalDeRegistros());
        }

        log.info("Fim validar");
    }
}
