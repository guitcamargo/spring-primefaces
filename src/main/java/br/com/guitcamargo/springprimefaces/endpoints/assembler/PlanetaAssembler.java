package br.com.guitcamargo.springprimefaces.endpoints.assembler;

import br.com.guitcamargo.springprimefaces.domain.PlanetaEntity;
import br.com.guitcamargo.springprimefaces.endpoints.PlanetaEndpoint;
import br.com.guitcamargo.springprimefaces.endpoints.resource.PlanetaResource;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlanetaAssembler extends RepresentationModelAssemblerSupport<PlanetaEntity, PlanetaResource> {

    public PlanetaAssembler() {
        super(PlanetaEndpoint.class, PlanetaResource.class);
    }

    @Override
    public PlanetaResource toModel(PlanetaEntity entity) {
        PlanetaResource planetaResource = new ModelMapper().map(entity, PlanetaResource.class);
        planetaResource.add(linkTo(methodOn(PlanetaEndpoint.class).findById(planetaResource.getId()))
                .withSelfRel());

        return planetaResource;
    }

    public PlanetaEntity toEntity(PlanetaResource resource) {
        PlanetaEntity planeta = new ModelMapper().map(resource, PlanetaEntity.class);
        return planeta;
    }

}
