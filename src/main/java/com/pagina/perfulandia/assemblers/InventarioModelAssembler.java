package com.pagina.perfulandia.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.pagina.perfulandia.Inventario.model.Inventario;
import com.pagina.perfulandia.hateoas.InventarioControllerV2;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(InventarioControllerV2.class).getInventarioById(entity.getId())).withSelfRel(),
                linkTo(methodOn(InventarioControllerV2.class).getAll()).withRel("Inventario"));
    }

}