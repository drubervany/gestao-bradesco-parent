package br.com.syshealth.gestao.bradesco.core.test;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.FixedFormat;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.HeaderSubFaturaDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.PremioDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.TitularDependenteDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.TraillerDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.TraillerObservacaoDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.TraillerSubFaturaDto;

/**
 * The Class FixedFormatTest.
 */
public class FixedFormatFaturaTest {

    /** The entrada. */
    private Scanner entrada;

    /**
     * Valida leitura linha cabecalho.
     *
     * @throws Exception
     *             the exception
     */
    // @Test
    public void validaLeituraLinhaCabecalho() throws Exception {

        FixedFormat ff = new FixedFormat();
        ff.setHeaderSize(180);
        ff.setHeaderSubFaturaSize(180);
        ff.setTitularDependenteSize(180);
        ff.setTraillerSubFaturaSize(180);
        ff.setTraillerObservacaoSize(180);
        ff.setTraillerSize(180);

        File files = new File("C:\\Users\\drdias1\\Downloads\\FM12073169\\"
        // FM11073169.txt"
        );

        for (File file : files.listFiles()) {

            System.out.print(file.getName());
            long tempoInicial = System.currentTimeMillis();

            PremioDto fatura = null;
            HeaderSubFaturaDto subFatura = null;
            entrada = new Scanner(file);
            while (entrada.hasNext()) {
                String linha = entrada.nextLine();

                switch (linha.substring(0, 1)) {
                case "1":
                    if (fatura == null) {
                        fatura = new PremioDto();
                        fatura = ff.readLines(linha, PremioDto.class);
                    } else {
                        PremioDto faturaErro = ff.readLines(linha, PremioDto.class);
                        throw new IllegalArgumentException("Header da " + fatura.getNomeDoEstipulante()
                                + " inesperado dentro do mesmo arquivo " + faturaErro.toString() + "!");
                    }
                    break;
                case "2":
                    if (fatura == null) {
                        throw new IllegalArgumentException("Header da fatura não foi localizado!");

                    }
                    if (subFatura == null || subFatura.getTraillerSubFatura() != null) {
                        subFatura = new HeaderSubFaturaDto();
                        subFatura = ff.readLines(linha, HeaderSubFaturaDto.class);
                        fatura.getHeaderSubFatura().add(subFatura);
                    } else {
                        throw new IllegalArgumentException("Sub Fatura " + subFatura.getNumeroDaSubfatura()
                                + " não encerrada adequadamente, faltou o trailler da subFatura!");
                    }
                    break;
                case "3":
                    if (subFatura != null)
                        subFatura.getTitularDependente().add(ff.readLines(linha, TitularDependenteDto.class));
                    else
                        throw new IllegalArgumentException("Não existe subFatura para adicionar as faturas!");
                    break;
                case "4":
                    if (subFatura != null) {
                        TraillerSubFaturaDto traillerSubFatura = null;
                        TraillerObservacaoDto traillerObersevacao = null;

                        if (linha.substring(5, 6).trim().toUpperCase().equals("(")) {
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
                        throw new IllegalArgumentException(
                                "Não existe subFatura para adicionar o trailler da subfatura!");

                    break;
                case "5":
                    if (fatura != null) {
                        fatura.setTrailler(ff.readLines(linha, TraillerDto.class));
                    } else {
                        throw new IllegalArgumentException("Não existe Fatura!");
                    }
                    break;
                default:
                    break;
                }
            }

            for (HeaderSubFaturaDto sub : fatura.getHeaderSubFatura()) {

                Map<String, Double> titularDependentePositivos = new HashMap<>();
                Map<String, Double> titularDependenteNegativos = new HashMap<>();

                BigDecimal totalTitularDependente = new BigDecimal(0);

                for (TitularDependenteDto td : sub.getTitularDependente()) {

                    String tipoLancamento = "(" + td.getTipoDeLancamento() + ")";

                    if (td.getCodigoDoLancamento() < 50) {
                        BigDecimal totalPositivos = titularDependentePositivos.get(tipoLancamento) == null
                                ? BigDecimal.ZERO : new BigDecimal(titularDependentePositivos.get(tipoLancamento));

                        titularDependentePositivos.put(tipoLancamento,
                                totalPositivos.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()
                                        + td.getValorDoLancamento());
                    } else {
                        BigDecimal totalNegativos = titularDependenteNegativos.get(tipoLancamento) == null
                                ? BigDecimal.ZERO : new BigDecimal(titularDependenteNegativos.get(tipoLancamento));

                        titularDependenteNegativos.put(tipoLancamento,
                                totalNegativos.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()
                                        + td.getValorDoLancamento());
                    }

                    if (td.getCodigoDoLancamento() > 49)
                        totalTitularDependente = new BigDecimal(td.getValorDoLancamento().doubleValue() * -1)
                                .add(new BigDecimal(totalTitularDependente.doubleValue()));
                    else
                        totalTitularDependente = new BigDecimal(td.getValorDoLancamento().doubleValue())
                                .setScale(2, BigDecimal.ROUND_HALF_UP)
                                .add(new BigDecimal(totalTitularDependente.doubleValue()))
                                .setScale(2, BigDecimal.ROUND_HALF_UP);
                }

                Map<String, Double> positivos = new HashMap<>();
                Map<String, Double> negativos = new HashMap<>();
                Map<String, Double> totais = new HashMap<>();
                Double traillerSub = new Double(0);
                int sinal = 1;
                Iterator<TraillerSubFaturaDto> iterator = sub.getTraillerSubFatura().stream()
                        .filter(s -> s.getNumeroDaSubfatura().intValue() == sub.getNumeroDaSubfatura().intValue())
                        .iterator();
                while (iterator.hasNext()) {
                    TraillerSubFaturaDto next = iterator.next();

                    String tipoLancamento = next.getTipoDeLancamento().substring(0, 4).replace(" ", "");

                    if (next.getTipoDeLancamento().substring(0, 4).equals("(TC)"))
                        sinal = -1;

                    if (!tipoLancamento.equals("(TC)") && !tipoLancamento.equals("(TD)")
                            && !tipoLancamento.equals("(TS)")) {
                        traillerSub += next.getTotLancamentosValor() * sinal;
                        if (next.getTotLancamentosValor() != 0)
                            if (sinal > 0)
                                positivos.put(tipoLancamento, next.getTotLancamentosValor());
                            else
                                negativos.put(tipoLancamento, next.getTotLancamentosValor());
                    } else
                        totais.put(tipoLancamento, next.getTotLancamentosValor());

                }

                double tolerance = 0.000001;
                for (Entry<String, Double> entry : positivos.entrySet()) {

                    if (Math.abs(entry.getValue() - titularDependentePositivos.get(entry.getKey())) > tolerance) {
                        throw new IllegalArgumentException("Valor Total positivos do arquivo " + entry.getKey()
                                + " não confere, total Titular e Dependente: "
                                + titularDependentePositivos.get(entry.getKey()) + " o trailler " + entry.getValue());
                    }
                }

                for (Entry<String, Double> entry : negativos.entrySet()) {

                    if (Math.abs(entry.getValue() - titularDependenteNegativos.get(entry.getKey())) > tolerance) {
                        throw new IllegalArgumentException("Valor Total negativos do arquivo " + entry.getKey()
                                + " não confere, total Titular e Dependente: "
                                + titularDependenteNegativos.get(entry.getKey()) + " o trailler " + entry.getValue());
                    }
                }

                if ((Math.abs(totalTitularDependente.doubleValue()) - totais.get("(TS)")) > tolerance) {
                    throw new IllegalArgumentException(
                            "Valor Total do arquivo não confere, total Titular e Dependente: " + totalTitularDependente
                                    + " o trailler " + totais.get("(TS)"));
                }
            }

            // execução do método
            System.out.println(
                    "o arquivo " + file.getName() + " executou em " + (System.currentTimeMillis() - tempoInicial));

        }
    }
}
