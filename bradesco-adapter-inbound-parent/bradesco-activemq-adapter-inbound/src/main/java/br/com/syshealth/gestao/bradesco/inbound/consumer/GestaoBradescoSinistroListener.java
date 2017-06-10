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
public class GestaoBradescoSinistroListener {

    private static final Logger log = LoggerFactory.getLogger(GestaoBradescoSinistroListener.class);

    @Autowired
    @Qualifier("sinistro")
    private GestaoBradescoListenerInputService service;

    @JmsListener(destination = "${jms.queue.bradesco.sinistro}")
    public void receive(String msg) {

        long tempoInicial = System.currentTimeMillis();

        log.info("Processar mensagem sinistro");

        try {
            service.processaMensagem(msg);
        } catch (CoreValidationException e) {
            log.error("Erro mensagem sinistro {}", e);
        }

        log.info("Mensagem processada sinistro");

        log.info("a sinistro msg executou em {}", System.currentTimeMillis() - tempoInicial);

    }
}
