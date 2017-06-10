package br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.entity.GestaoBradescoFaturaEntity;

public interface GestaoBradescoFaturaRepository extends MongoRepository<GestaoBradescoFaturaEntity, String> {

}
