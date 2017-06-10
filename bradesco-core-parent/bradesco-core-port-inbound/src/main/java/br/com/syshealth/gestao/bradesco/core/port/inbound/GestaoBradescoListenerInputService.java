package br.com.syshealth.gestao.bradesco.core.port.inbound;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.CoreValidationException;

/**
 * The Interface IdentificadorInputService.
 * 
 * @author Danilo.Rubervany
 */
public interface GestaoBradescoListenerInputService {

    void processaMensagem(String msg) throws CoreValidationException;

}
