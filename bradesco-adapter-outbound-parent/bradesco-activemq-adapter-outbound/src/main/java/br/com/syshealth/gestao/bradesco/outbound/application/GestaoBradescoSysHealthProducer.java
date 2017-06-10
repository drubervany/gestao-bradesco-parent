package br.com.syshealth.gestao.bradesco.outbound.application;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.syshealth.commons.dto.Cadastro;
import br.com.syshealth.commons.dto.Premio;
import br.com.syshealth.commons.dto.Sinistro;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.CoreValidationException;
import br.com.syshealth.gestao.bradesco.core.port.outbound.JmsClient;

@Service
public class GestaoBradescoSysHealthProducer implements JmsClient {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    @Qualifier("queueFatura")
    private Queue queueFatura;

    @Autowired
    @Qualifier("queueSinistro")
    private Queue queueSinistro;

    @Autowired
    @Qualifier("queueCadastro")
    private Queue queueCadastro;

    @Override
    public void sendPremio(Premio premio) {

        this.jmsMessagingTemplate.convertAndSend(this.queueFatura, premio.toJson());
    }

    @Override
    public void sendSinistro(Sinistro sinistro) {

        this.jmsMessagingTemplate.convertAndSend(this.queueSinistro, sinistro.toJson());
    }

    @Override
    public void sendCadastro(Cadastro cadastro) throws CoreValidationException {

        this.jmsMessagingTemplate.convertAndSend(this.queueCadastro, cadastro.toJson());
    }
}