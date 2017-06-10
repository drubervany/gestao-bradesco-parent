package br.com.syshealth.gestao.bradesco.outbound.application;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class GestaoBradescoSysHealthConfiguration {

    @Value("${jms.queue.syshealth.fatura}")
    private String queueFatura;

    @Value("${jms.queue.syshealth.sinistro}")
    private String queueSinistro;

    @Value("${jms.queue.syshealth.cadastro}")
    private String queueCadastro;

    @Bean(name = "queueFatura")
    public Queue queueFatura() {
        return new ActiveMQQueue(queueFatura);
    }

    @Bean(name = "queueSinistro")
    public Queue queueSinistro() {
        return new ActiveMQQueue(queueSinistro);
    }

    @Bean(name = "queueCadastro")
    public Queue queueCadastro() {
        return new ActiveMQQueue(queueCadastro);
    }
}