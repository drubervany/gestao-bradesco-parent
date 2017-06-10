package br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.entity.GestaoBradescoSinistroEntity;

public interface GestaoBradescoSinistroRepository extends MongoRepository<GestaoBradescoSinistroEntity, String> {

}
