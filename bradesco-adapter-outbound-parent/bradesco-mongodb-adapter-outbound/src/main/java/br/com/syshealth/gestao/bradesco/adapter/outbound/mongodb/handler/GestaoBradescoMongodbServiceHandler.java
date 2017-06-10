package br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.entity.GestaoBradescoCadastroEntity;
import br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.entity.GestaoBradescoFaturaEntity;
import br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.entity.GestaoBradescoSinistroEntity;
import br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.repository.GestaoBradescoCadastroRepository;
import br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.repository.GestaoBradescoFaturaRepository;
import br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.repository.GestaoBradescoSinistroRepository;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.CadastroDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.PremioDto;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.SinistroDto;
import br.com.syshealth.gestao.bradesco.core.port.outbound.GestaoBradescoOutboundDbService;

@Service
public class GestaoBradescoMongodbServiceHandler implements GestaoBradescoOutboundDbService {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(GestaoBradescoMongodbServiceHandler.class);

    /** The dao. */
    @Autowired
    private GestaoBradescoFaturaRepository repositoryPremio;

    @Autowired
    private GestaoBradescoSinistroRepository repositorySinistro;

    @Autowired
    private GestaoBradescoCadastroRepository repositoryCadastro;

    /*
     * (non-Javadoc)
     * 
     * @see br.com.syshealth.gestao.bradesco.core.port.outbound.
     * GestaoBradescoOutboundDbService#inserir(java.lang.String)
     */
    @Override
    public void inserir(PremioDto premio) {
        log.info("Inserir no mongodb:: {}", premio.toJson());
        GestaoBradescoFaturaEntity entity = new GestaoBradescoFaturaEntity();
        entity.setPremio(premio);
        repositoryPremio.save(entity);
        log.info("premio salvo :: {}", entity.toJson());

    }

    @Override
    public void inserir(SinistroDto sinistro) {
        log.info("Inserir no mongodb:: {}", sinistro.toJson());
        GestaoBradescoSinistroEntity entity = new GestaoBradescoSinistroEntity();
        entity.setSinistro(sinistro);
        log.info("salvar premio:: {}", sinistro.toJson());

        repositorySinistro.save(entity);
        log.info("premio salvo :: {}", entity.toJson());

    }

    @Override
    public void inserir(CadastroDto cadastro) {
        log.info("Inserir no mongodb:: {}", cadastro.toJson());
        GestaoBradescoCadastroEntity entity = new GestaoBradescoCadastroEntity();
        entity.setCadastro(cadastro);
        repositoryCadastro.save(entity);
        log.info("premio salvo :: {}", entity.toJson());

    }

}
