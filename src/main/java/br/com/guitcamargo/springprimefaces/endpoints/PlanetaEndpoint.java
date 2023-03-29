package br.com.guitcamargo.springprimefaces.endpoints;


import br.com.guitcamargo.springprimefaces.domain.PlanetaEntity;
import br.com.guitcamargo.springprimefaces.endpoints.assembler.PlanetaAssembler;
import br.com.guitcamargo.springprimefaces.endpoints.resource.PlanetaResource;
import br.com.guitcamargo.springprimefaces.services.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Validated
@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class PlanetaEndpoint {

    public static final String PLANETA_BASE_URI = "/planetas";
    private static final String PLANETA_PATH = PLANETA_BASE_URI + "/{idPlaneta}";
    private static final String PLANETA_MATRIX = PLANETA_BASE_URI + "{matrixVariables}";

    @Autowired
    PlanetaService service;

    @Autowired
    PlanetaAssembler assembler;

    @Autowired
    PagedResourcesAssembler pagedResourceAssembler;

    @GetMapping(value = PLANETA_PATH)
    public ResponseEntity<PlanetaResource> findById(@NotNull @PathVariable("idPlaneta") final Long idPlaneta) {

        PlanetaEntity planeta = service.findById(idPlaneta);
        return ResponseEntity.ok(assembler.toModel(planeta));
    }

    @PostMapping(value = PLANETA_BASE_URI)
    public ResponseEntity<PlanetaResource> create(@Valid @RequestBody PlanetaResource resource) {

        PlanetaEntity entity  = service.create(assembler.toEntity(resource));
        return new ResponseEntity<>(assembler.toModel(entity), HttpStatus.CREATED);

    }

    @DeleteMapping(value = PLANETA_PATH)
    public ResponseEntity<?> delete(@PathVariable("idPlaneta") final Long idPlaneta) {

        service.delete(idPlaneta);
        return ResponseEntity.ok().build();
    }
}
