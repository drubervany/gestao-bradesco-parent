package br.com.syshealth.gestao.bradesco.core.test;

import java.io.File;
import java.util.Scanner;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.FixedFormat;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.MovimentoDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.SinistroDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.TraillerDto;

/**
 * The Class FixedFormatTest.
 */
public class FixedFormatSinistroTest {

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
        ff.setHeaderSize(500);
        ff.setMovimentoSize(500);
        ff.setTraillerSize(500);

        File files = new File("G:\\DANILO_BASE\\SysHealth\\SysHealth_arquivos\\Sinistro"
        // FM11073169.txt"
        );

        for (File file : files.listFiles()) {

            System.out.print(file.getName());
            long tempoInicial = System.currentTimeMillis();

            SinistroDto sinistro = null;
            entrada = new Scanner(file);
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

            Double valorPago = new Double(0);
            for (MovimentoDto movimento : sinistro.getMovimento()) {
                valorPago = valorPago + movimento.getValorPago();
            }

            double tolerance = 0.000001;
            if (Math.abs(valorPago - sinistro.getTrailler().getTotalDeValorPago()) > tolerance) {
                throw new IllegalArgumentException("Total valor pago diferentes " + valorPago + " <> "
                        + sinistro.getTrailler().getTotalDeValorPago().doubleValue());
            }

            if (sinistro.getMovimento().size() != sinistro.getTrailler().getTotalDeRegistros()) {
                throw new IllegalArgumentException("Total de registros diferentes " + sinistro.getMovimento().size() + " <> "
                        + sinistro.getTrailler().getTotalDeRegistros());
            }

            // execução do método
            System.out.println(
                    "o arquivo " + file.getName() + " executou em " + (System.currentTimeMillis() - tempoInicial));

        }
    }
}
