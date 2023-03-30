package br.com.guitcamargo.springprimefaces.repositories.specification;

import br.com.guitcamargo.springprimefaces.domain.PlanetaEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public interface PlanetaSpecification {

    static Specification<PlanetaEntity> nome (String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                StringUtils.isEmpty(nome) ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nome")), "%"+nome.toLowerCase()+"%");
    }


    static Specification<PlanetaEntity> clima (String clima) {
        return (root, criteriaQuery, criteriaBuilder) ->
                StringUtils.isEmpty(clima) ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("clima")), "%"+clima.toLowerCase()+"%");
    }

    static Specification<PlanetaEntity> terreno (String terreno) {
        return (root, criteriaQuery, criteriaBuilder) ->
                StringUtils.isEmpty(terreno) ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("terreno")), "%"+terreno.toLowerCase()+"%");
    }
}
