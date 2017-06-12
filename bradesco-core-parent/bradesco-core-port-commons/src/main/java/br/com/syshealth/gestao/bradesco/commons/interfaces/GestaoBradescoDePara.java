package br.com.syshealth.gestao.bradesco.commons.interfaces;

import org.springframework.stereotype.Component;

import br.com.syshealth.commons.enums.EstadoCivilEnum;
import br.com.syshealth.commons.enums.GrauParentescoEnum;
import br.com.syshealth.commons.enums.RedeReembolsoEnum;
import br.com.syshealth.commons.enums.SexoEnum;
import br.com.syshealth.commons.enums.SimNaoEnum;
import br.com.syshealth.commons.enums.TipoBeneficiarioEnum;
import br.com.syshealth.commons.utils.StringUtils;

@Component
public interface GestaoBradescoDePara {

    public static TipoBeneficiarioEnum retornaTipoBeneficiario(int complementoCertificado) {

        if (complementoCertificado == 0)
            return TipoBeneficiarioEnum.TITULAR;
        else if (complementoCertificado > 0)
            return TipoBeneficiarioEnum.DEPENDENTE;
        else
            throw new IllegalArgumentException(
                    "Codigo do TipoBeneficiario " + complementoCertificado + " não identificado!");
    }

    public static SexoEnum retornaSexo(int codigo) {
        switch (codigo) {
        case 1:
            return SexoEnum.MASCULINO;
        case 2:
            return SexoEnum.FEMININO;
        default:
            throw new IllegalArgumentException("Codigo do Sexo " + codigo + " não identificado!");
        }
    }

    public static GrauParentescoEnum retornaParentesco(int codigo) {

        switch (codigo) {
        case 1:
            return GrauParentescoEnum.CONJUGE;
        case 2:
            return GrauParentescoEnum.FILHO;
        case 3:
            return GrauParentescoEnum.MAE;
        case 4:
            return GrauParentescoEnum.PAI;
        case 5:
            return GrauParentescoEnum.SOGRO;
        case 6:
            return GrauParentescoEnum.SOGRA;
        case 7:
            return GrauParentescoEnum.TUTELATO;
        case 8:
            return GrauParentescoEnum.OUTROS;
        default:
            throw new IllegalArgumentException("Codigo do Parentesco " + codigo + " não identificado!");
        }
    }

    public static EstadoCivilEnum retonaEstadoCivil(Integer codigo) {

        switch (codigo) {
        case 1:
            return EstadoCivilEnum.SOLTEIRO;
        case 2:
            return EstadoCivilEnum.CASADO;
        case 3:
            return EstadoCivilEnum.VIUVO;
        case 4:
            return EstadoCivilEnum.OUTROS;
        default:
            throw new IllegalArgumentException("Codigo do EstadoCivil " + codigo + " não identificado!");
        }
    }

    public static RedeReembolsoEnum retornaRedeReembolso(String cpfCgcDoReferenciado) {
        if (cpfCgcDoReferenciado.equals("00000000000000"))
            return RedeReembolsoEnum.REEMBOLSO;
        return RedeReembolsoEnum.REDE;
    }

    public static SimNaoEnum retornaInternado(String numeroDoDocumento) {
        if (StringUtils.verificaSeExisteLetra(numeroDoDocumento))
            return SimNaoEnum.SIM;
        return SimNaoEnum.NAO;
    }

    public static Long retornaCodigoSegurado(Long numeroDoCertificado, Integer complementoDoCertificado) {
        return new Long(numeroDoCertificado.toString()
                + StringUtils.lpad(complementoDoCertificado.toString(), "0", 2));
    }

}
