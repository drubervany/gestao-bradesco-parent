package br.com.syshealth.gestao.bradesco.core.handler.fatura;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.CoreValidationException;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.PremioDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.HeaderSubFaturaDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.TitularDependenteDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.TraillerSubFaturaDto;

/**
 * The Class IdentificadorValidator.
 * 
 * @author Danilo.Rubervany
 */
@Service
public class GestaoBradescoFaturaValidator {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(GestaoBradescoFaturaValidator.class);

    /**
     * Instantiates a new identificador validator.
     */
    public GestaoBradescoFaturaValidator() {

    }

    /**
     * Validar.
     * 
     * @param fatura
     * @throws CoreValidationException
     *             the core validation exception
     */
    public void validar(PremioDto fatura) throws CoreValidationException {
        log.info("inicio validar");

        for (HeaderSubFaturaDto sub : fatura.getHeaderSubFatura()) {

            Map<String, Double> titularDependentePositivos = new HashMap<>();
            Map<String, Double> titularDependenteNegativos = new HashMap<>();

            BigDecimal totalTitularDependente = new BigDecimal(0);

            for (TitularDependenteDto td : sub.getTitularDependente()) {

                String tipoLancamento = "(" + td.getTipoDeLancamento() + ")";

                if (td.getCodigoDoLancamento() < 50) {
                    BigDecimal totalPositivos = titularDependentePositivos.get(tipoLancamento) == null ? BigDecimal.ZERO
                            : new BigDecimal(titularDependentePositivos.get(tipoLancamento));

                    titularDependentePositivos.put(tipoLancamento,
                            totalPositivos.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()
                                    + td.getValorDoLancamento());
                } else {
                    BigDecimal totalNegativos = titularDependenteNegativos.get(tipoLancamento) == null ? BigDecimal.ZERO
                            : new BigDecimal(titularDependenteNegativos.get(tipoLancamento));

                    titularDependenteNegativos.put(tipoLancamento,
                            totalNegativos.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()
                                    + td.getValorDoLancamento());
                }

                if (td.getCodigoDoLancamento() > 49)
                    totalTitularDependente = BigDecimal.valueOf(td.getValorDoLancamento().doubleValue() * -1)
                            .add(BigDecimal.valueOf(totalTitularDependente.doubleValue()));
                else
                    totalTitularDependente = BigDecimal.valueOf(td.getValorDoLancamento().doubleValue())
                            .setScale(2, BigDecimal.ROUND_HALF_UP)
                            .add(BigDecimal.valueOf(totalTitularDependente.doubleValue()))
                            .setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            Map<String, Double> positivos = new HashMap<>();
            Map<String, Double> negativos = new HashMap<>();
            Map<String, Double> totais = new HashMap<>();
            int sinal = 1;
            Iterator<TraillerSubFaturaDto> iterator = sub.getTraillerSubFatura().stream()
                    .filter(s -> s.getNumeroDaSubfatura().intValue() == sub.getNumeroDaSubfatura().intValue())
                    .iterator();
            while (iterator.hasNext()) {
                TraillerSubFaturaDto next = iterator.next();

                String tipoLancamento = next.getTipoDeLancamento().substring(0, 4).replace(" ", "");

                if ("(TC)".equals(next.getTipoDeLancamento().substring(0, 4)))
                    sinal = -1;

                if (!"(TC)".equals(tipoLancamento) && !"(TD)".equals(tipoLancamento)
                        && !"(TS)".equals(tipoLancamento)) {

                    if (next.getTotLancamentosValor() != 0) {
                        if (sinal > 0)
                            positivos.put(tipoLancamento, next.getTotLancamentosValor());
                        else
                            negativos.put(tipoLancamento, next.getTotLancamentosValor());
                    }
                } else
                    totais.put(tipoLancamento, next.getTotLancamentosValor());

            }

            double tolerance = 0.000001;
            for (Entry<String, Double> entry : positivos.entrySet()) {

                if (Math.abs(entry.getValue() - titularDependentePositivos.get(entry.getKey())) > tolerance) {
                    throw new CoreValidationException("Valor Total positivos do arquivo " + entry.getKey()
                            + " não confere, total Titular e Dependente: "
                            + titularDependentePositivos.get(entry.getKey()) + " o trailler " + entry.getValue());
                }
            }

            for (Entry<String, Double> entry : negativos.entrySet()) {

                if (Math.abs(entry.getValue() - titularDependenteNegativos.get(entry.getKey())) > tolerance) {
                    throw new CoreValidationException("Valor Total negativos do arquivo " + entry.getKey()
                            + " não confere, total Titular e Dependente: "
                            + titularDependenteNegativos.get(entry.getKey()) + " o trailler " + entry.getValue());
                }
            }

            if ((Math.abs(totalTitularDependente.doubleValue()) - totais.get("(TS)")) > tolerance) {
                throw new CoreValidationException("Valor Total do arquivo não confere, total Titular e Dependente: "
                        + totalTitularDependente + " o trailler " + totais.get("(TS)"));
            }
        }

        log.info("Fim validar");
    }
}
