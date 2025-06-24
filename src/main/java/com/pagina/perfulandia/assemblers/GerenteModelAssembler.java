package com.pagina.perfulandia.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.pagina.perfulandia.Gerente.model.Gerente;
import com.pagina.perfulandia.hateoas.GerenteControllerV2;

@Component
public class GerenteModelAssembler implements RepresentationModelAssembler<Gerente, EntityModel<Gerente>> {

    @Override
    public EntityModel<Gerente> toModel(Gerente entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(GerenteControllerV2.class).getGerenteById(entity.getId())).withSelfRel(),
                linkTo(methodOn(GerenteControllerV2.class).getAll()).withRel("Gerente"));
    }

}
