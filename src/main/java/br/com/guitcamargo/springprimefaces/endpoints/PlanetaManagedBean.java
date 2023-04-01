package br.com.guitcamargo.springprimefaces.endpoints;

import br.com.guitcamargo.springprimefaces.domain.PlanetaEntity;
import br.com.guitcamargo.springprimefaces.services.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("planetaMB")
@ViewScoped
@DependsOn("loadData")
public class PlanetaManagedBean {

    private List<PlanetaEntity> planetas = new ArrayList<>();


    private PlanetaEntity planeta = new PlanetaEntity();

    @Autowired
    private PlanetaService planetaService;


    @PostConstruct
    public List<PlanetaEntity> findAll(){
        planetas = planetaService.findAll();
        return planetas;
    }

    public void remove(){
        this.planetaService.delete(planeta.getId());
        this.findAll();
    }

    public Integer getSizeList() {
        return planetas.size();
    }

    public void setSizeList(Integer size) {
    }



    public List<PlanetaEntity> getPlanetas() {
        return planetas;
    }

    public void setPlanetas(List<PlanetaEntity> planetas) {
        this.planetas = planetas;
    }

    public PlanetaEntity getPlaneta() {
        return planeta;
    }

    public void setPlaneta(PlanetaEntity planeta) {
        this.planeta = planeta;
    }

    public PlanetaService getPlanetaService() {
        return planetaService;
    }

    public void setPlanetaService(PlanetaService planetaService) {
        this.planetaService = planetaService;
    }
}
