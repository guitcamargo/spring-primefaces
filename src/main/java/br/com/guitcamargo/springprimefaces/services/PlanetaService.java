package br.com.guitcamargo.springprimefaces.services;

import br.com.guitcamargo.springprimefaces.domain.PlanetaEntity;
import br.com.guitcamargo.springprimefaces.infra.error.ErrorEnum;
import br.com.guitcamargo.springprimefaces.infra.exceptions.NotFoundException;
import br.com.guitcamargo.springprimefaces.repositories.PlanetaManager;
import br.com.guitcamargo.springprimefaces.repositories.PlanetaRepository;
import br.com.guitcamargo.springprimefaces.repositories.specification.PlanetaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class PlanetaService {

    @Autowired
    private PlanetaRepository repository;

    @Autowired
    private PlanetaManager planetaManager;

    public PlanetaService() {
    }

    public PlanetaEntity create(PlanetaEntity entity) {
        return repository.saveAndFlush(entity);
    }

    public PlanetaEntity findById(Long idPlaneta) {
        PlanetaEntity entity =  repository.findById(idPlaneta)
                .orElseThrow(() -> new NotFoundException(ErrorEnum.PLANETA_NOT_FOUND));
        this.verifyAndSetQuantityFilmsByName(entity);
        return entity;
    }

    public void delete(Long idPlaneta) {
        //verifica se existe o registro para nao precisar tratar exception do banco.
        this.findById(idPlaneta);
        repository.deleteById(idPlaneta);
    }

    //Poderia utilizar o FindAll(Example<S>) para ser menos verboso, mas particularmente gosto da estrutura do specification
    public Page<PlanetaEntity> findByFilter(Pageable pageable, String nome, String clima, String terreno) {
        Page<PlanetaEntity> entities = repository.findAll(
                where(PlanetaSpecification.nome(nome))
                        .and(PlanetaSpecification.clima(clima)
                                .and(PlanetaSpecification.terreno(terreno))),
                pageable
        );

        entities.stream().forEach( entity -> {
            this.verifyAndSetQuantityFilmsByName(entity);
        });

        return entities;
    }

    private PlanetaEntity verifyAndSetQuantityFilmsByName(PlanetaEntity entity){
        Integer quantityFilms = this.planetaManager.getPlanetasFilmes().get(entity.getNome().toUpperCase());
        if(quantityFilms != null)
            entity.setFilms(quantityFilms);
        return entity;
    }
}
