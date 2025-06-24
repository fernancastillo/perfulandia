package com.pagina.perfulandia.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.pagina.perfulandia.Proveedor.model.Proveedor;
import com.pagina.perfulandia.hateoas.ProveedorControllerV2;


@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>> {

    @Override
    public EntityModel<Proveedor> toModel(Proveedor entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ProveedorControllerV2.class).getProveedorById(entity.getId())).withSelfRel(),
                linkTo(methodOn(ProveedorControllerV2.class).getAll()).withRel("Proveedor"));
    }
}
