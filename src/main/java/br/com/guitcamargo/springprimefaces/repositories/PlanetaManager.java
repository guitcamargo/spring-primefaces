package br.com.guitcamargo.springprimefaces.repositories;

import br.com.guitcamargo.springprimefaces.domain.PlanetasResponseDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PlanetaManager {

    private final RestTemplate restTemplate;

    static final String PATH_INITIAL = "https://swapi.dev/api/planets/";
    private Map<String, Integer> planetsAndMovies = new HashMap<>();

    public PlanetaManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        if(planetsAndMovies.isEmpty()) {
           this.populatePlanetsAndQuantityMovies();
        }
        System.out.println("Informações de planetas carregadas");
    }

    private void populatePlanetsAndQuantityMovies(){
        this.getValuesAndAddMap(PATH_INITIAL);
    }

    private void getValuesAndAddMap(String path) {
        System.out.println("Carregando planteas e suas informações:"+path);
        ResponseEntity<PlanetasResponseDTO> exchange =
                restTemplate.exchange(path, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), PlanetasResponseDTO.class);

        PlanetasResponseDTO response = exchange.getBody();

        response.getResults().stream().forEach(planet -> {
            this.putPlanetasFilmes(planet.getName().toUpperCase(), planet.getFilms().isEmpty() ? 0 : planet.getFilms().size());
        });

        if(response.hasNextPage()) {
            this.getValuesAndAddMap(response.getNext());
        }
    }


    public Map<String, Integer> getPlanetasFilmes() {
        return this.planetsAndMovies;
    }

    private void putPlanetasFilmes(String planeta, Integer quantityFilms) {
        this.planetsAndMovies.put(planeta, quantityFilms);

    }

}
