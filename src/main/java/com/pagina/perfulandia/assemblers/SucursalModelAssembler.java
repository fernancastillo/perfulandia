package com.pagina.perfulandia.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.pagina.perfulandia.Sucursal.model.Sucursal;
import com.pagina.perfulandia.hateoas.SucursalControllerV2;



@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>> {

    @Override
    public EntityModel<Sucursal> toModel(Sucursal entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(SucursalControllerV2.class).getSucursalById(entity.getId())).withSelfRel(),
                linkTo(methodOn(SucursalControllerV2.class).getAll()).withRel("Sucursal"));
    }

}
