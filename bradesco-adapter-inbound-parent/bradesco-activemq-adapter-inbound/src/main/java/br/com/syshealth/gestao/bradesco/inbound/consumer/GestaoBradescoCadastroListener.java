package br.com.syshealth.gestao.bradesco.inbound.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.CoreValidationException;
import br.com.syshealth.gestao.bradesco.core.port.inbound.GestaoBradescoListenerInputService;

@Component
public class GestaoBradescoCadastroListener {

    private static final Logger log = LoggerFactory.getLogger(GestaoBradescoCadastroListener.class);

    @Autowired
    @Qualifier("cadastro")
    private GestaoBradescoListenerInputService service;

    @JmsListener(destination = "${jms.queue.bradesco.cadastro}")
    public void receive(String msg) {

        long tempoInicial = System.currentTimeMillis();

        log.info("Processar mensagem sinistro");

        try {
            service.processaMensagem(msg);
        } catch (CoreValidationException e) {
            log.error("Erro mensagem sinistro {}", e);
        }

        log.info("Mensagem processada sinistro");

        log.info("o cadastro msg executou em {}", System.currentTimeMillis() - tempoInicial);

    }
}
