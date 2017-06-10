package br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.entity.GestaoBradescoCadastroEntity;

public interface GestaoBradescoCadastroRepository extends MongoRepository<GestaoBradescoCadastroEntity, String> {

}
