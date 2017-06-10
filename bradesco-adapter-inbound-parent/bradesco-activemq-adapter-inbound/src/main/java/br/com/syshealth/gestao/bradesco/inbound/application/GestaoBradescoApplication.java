package br.com.syshealth.gestao.bradesco.inbound.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import br.com.syshealth.gestao.bradesco.core.handler.cadastro.GestaoBradescoCadastroServiceHandler;
import br.com.syshealth.gestao.bradesco.core.handler.fatura.GestaoBradescoFaturaServiceHandler;
import br.com.syshealth.gestao.bradesco.core.handler.sinistro.GestaoBradescoSinistroServiceHandler;
import br.com.syshealth.gestao.bradesco.core.port.inbound.GestaoBradescoListenerInputService;

@ComponentScan(basePackages = { "br.com.syshealth.gestao.bradesco" })
@SpringBootApplication
public class GestaoBradescoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoBradescoApplication.class, args);
    }

    @Bean(name = "cadastro")
    public GestaoBradescoListenerInputService gestaoBradescoListenerCadastroService() {
        return new GestaoBradescoCadastroServiceHandler();
    }

    @Bean(name = "fatura")
    public GestaoBradescoListenerInputService gestaoBradescoListenerFaturaService() {
        return new GestaoBradescoFaturaServiceHandler();
    }

    @Bean(name = "sinistro")
    public GestaoBradescoListenerInputService gestaoBradescoListenerSinistroService() {
        return new GestaoBradescoSinistroServiceHandler();
    }
}
