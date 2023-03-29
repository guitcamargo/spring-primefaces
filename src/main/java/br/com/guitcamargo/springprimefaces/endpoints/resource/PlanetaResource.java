package br.com.guitcamargo.springprimefaces.endpoints.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonPropertyOrder({ "id", "nome", "clima", "terreno"})
@Relation(value = "planeta", collectionRelation = "planetas")
public class PlanetaResource extends RepresentationModel<PlanetaResource> {

    private Long id;

    @NotBlank
    @Size(max = 250, min = 1)
    private String nome;

    @NotBlank
    @Size(max = 80, min = 1)
    private String clima;

    @NotBlank
    @Size(max = 80, min = 1)
    private String terreno;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }
}
