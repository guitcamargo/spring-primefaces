package br.com.guitcamargo.springprimefaces.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PLANETA")
public class PlanetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_PLANETA", nullable = false)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CLIMA")
    private String clima;

    @Column(name = "TERRENO")
    private String terreno;

    @Transient
    private Integer films;

    public PlanetaEntity(){

    }

    public PlanetaEntity(Long id, String nome, String clima, String terreno) {
        this.id = id;
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

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

    public Integer getFilms() {
        return films;
    }

    public void setFilms(Integer films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "PlanetaEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", clima='" + clima + '\'' +
                ", terreno='" + terreno + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetaEntity that = (PlanetaEntity) o;
        return id.equals(that.id) && nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
