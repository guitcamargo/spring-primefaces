package br.com.guitcamargo.springprimefaces.infra;


import br.com.guitcamargo.springprimefaces.domain.PlanetaEntity;
import br.com.guitcamargo.springprimefaces.services.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LoadData {

    @Value("${load-db-datas}")
    private boolean loadDatas;

    @Autowired
    private PlanetaService planetaService;


    @PostConstruct
    public void loadDatas(){
        if(loadDatas){
            PlanetaEntity entity = new PlanetaEntity();
            PlanetaEntity entity2 = new PlanetaEntity();
            PlanetaEntity entity3 = new PlanetaEntity();

            entity.setNome("Tatooine");
            entity.setClima("Clima 1");
            entity.setTerreno("Terreno 1");

            entity2.setNome("Alderaan");
            entity2.setClima("Clima 2");
            entity2.setTerreno("Terreno 2");

            entity3.setNome("Mirial");
            entity3.setClima("Clima 3");
            entity3.setTerreno("Terreno 3");

            planetaService.create(entity);
            planetaService.create(entity2);
            planetaService.create(entity3);
            System.out.println("PLANETAS CADASTRADOS");
        }
    }

}
