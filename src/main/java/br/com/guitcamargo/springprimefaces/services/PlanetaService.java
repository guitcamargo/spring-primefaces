package br.com.guitcamargo.springprimefaces.services;

import br.com.guitcamargo.springprimefaces.domain.PlanetaEntity;
import br.com.guitcamargo.springprimefaces.infra.error.ErrorEnum;
import br.com.guitcamargo.springprimefaces.infra.exceptions.NotFoundException;
import br.com.guitcamargo.springprimefaces.repositories.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetaService {

    @Autowired
    private PlanetaRepository repository;
    public PlanetaService() {
    }

    public PlanetaEntity create(PlanetaEntity entity) {
        return repository.saveAndFlush(entity);
    }

    public PlanetaEntity findById(Long idPlaneta) {
        return repository.findById(idPlaneta)
                .orElseThrow(() -> new NotFoundException(ErrorEnum.PLANETA_NOT_FOUND));
    }

    public void delete(Long idPlaneta) {
        //verifica se existe o registro para nao precisar tratar exception do banco.
        this.findById(idPlaneta);
        repository.deleteById(idPlaneta);
    }
}
