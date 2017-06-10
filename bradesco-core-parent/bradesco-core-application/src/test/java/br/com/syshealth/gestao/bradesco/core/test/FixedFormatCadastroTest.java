package br.com.syshealth.gestao.bradesco.core.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.FixedFormat;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.CadastroDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.DadosBancariosDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.DependenteDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.TitularComplementoDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.TitularDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.TraillerDto;

/**
 * The Class FixedFormatTest.
 */
public class FixedFormatCadastroTest {

    /** The entrada. */
    private Scanner entrada;

    /**
     * Valida leitura linha cabecalho.
     *
     * @throws Exception
     *             the exception
     */
    // @Test
    public void validaLeituraLinhaCabecalho() {

        FixedFormat ff = new FixedFormat();
        ff.setHeaderSize(180);
        ff.setTitularSize(180);
        ff.setTitularComplementoSize(180);
        ff.setDadosBancariosSize(180);
        ff.setDependenteSize(180);
        ff.setTraillerSize(180);

        File files = new File("G:\\DANILO_BASE\\SysHealth\\SysHealth_arquivos\\Cadastro\\");

        for (File file : files.listFiles()) {

            System.out.print(file.getName());
            long tempoInicial = System.currentTimeMillis();

            CadastroDto cadastro = null;
            TitularDto titular = null;
            try {
                entrada = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

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

            // execução do método
            System.out.println(
                    "o arquivo " + file.getName() + " executou em " + (System.currentTimeMillis() - tempoInicial));

        }
    }
}
