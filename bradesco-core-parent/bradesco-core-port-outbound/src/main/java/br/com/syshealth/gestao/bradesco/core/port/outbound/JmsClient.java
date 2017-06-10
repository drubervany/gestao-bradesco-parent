package br.com.syshealth.gestao.bradesco.core.port.outbound;

import br.com.syshealth.commons.dto.Cadastro;
import br.com.syshealth.commons.dto.Premio;
import br.com.syshealth.commons.dto.Sinistro;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.CoreValidationException;

public interface JmsClient {

    void sendPremio(Premio premio) throws CoreValidationException;

    void sendSinistro(Sinistro sinistro) throws CoreValidationException;

    void sendCadastro(Cadastro cadastro) throws CoreValidationException;
}
