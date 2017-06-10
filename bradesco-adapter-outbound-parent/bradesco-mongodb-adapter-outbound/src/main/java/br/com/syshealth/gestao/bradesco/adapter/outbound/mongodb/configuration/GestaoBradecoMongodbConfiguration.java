package br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb")
@ComponentScan(basePackages = { "br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb" })
public class GestaoBradecoMongodbConfiguration {

}
