package com.pagina.perfulandia.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.pagina.perfulandia.Venta.model.Venta;
import com.pagina.perfulandia.hateoas.VentaControllerV2;

@Component
public class VentaModelAssembler implements RepresentationModelAssembler<Venta, EntityModel<Venta>> {

    @Override
    public EntityModel<Venta> toModel(Venta entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(VentaControllerV2.class).getVentaById(entity.getId())).withSelfRel(),
                linkTo(methodOn(VentaControllerV2.class).getAll()).withRel("Venta"));
    }
}
