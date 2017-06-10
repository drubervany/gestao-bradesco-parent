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
public class GestaoBradescoFaturaListener {

    private static final Logger log = LoggerFactory.getLogger(GestaoBradescoFaturaListener.class);

    @Autowired
    @Qualifier("fatura")
    private GestaoBradescoListenerInputService service;

    @JmsListener(destination = "${jms.queue.bradesco.fatura}")
    public void receive(String msg) {

        long tempoInicial = System.currentTimeMillis();

        log.info("Processar mensagem fatura");

        try {
            service.processaMensagem(msg);
        } catch (CoreValidationException e) {
            log.error("Erro mensagem fatura {}", e);
        }

        log.info("Mensagem processada fatura");

        log.info("a fatura msg executou em {}", System.currentTimeMillis() - tempoInicial);

    }
}
