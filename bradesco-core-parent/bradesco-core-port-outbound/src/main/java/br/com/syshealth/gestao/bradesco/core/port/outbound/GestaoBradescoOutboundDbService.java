package br.com.syshealth.gestao.bradesco.core.port.outbound;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.CadastroDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.PremioDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.SinistroDto;

/**
 * The Interface IdentificadorOutboundDbService.
 */
public interface GestaoBradescoOutboundDbService {

    void inserir(PremioDto fatura);

    void inserir(SinistroDto sinistro);

    void inserir(CadastroDto cadastro);
}
