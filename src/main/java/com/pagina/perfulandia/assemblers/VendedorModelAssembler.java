package com.pagina.perfulandia.assemblers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.pagina.perfulandia.Vendedor.model.Vendedor;
import com.pagina.perfulandia.hateoas.VendedorControllerV2;



@Component
public class VendedorModelAssembler implements RepresentationModelAssembler<Vendedor, EntityModel<Vendedor>> {

    @Override
    public EntityModel<Vendedor> toModel(Vendedor entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(VendedorControllerV2.class).getVendedorById(entity.getId())).withSelfRel(),
                linkTo(methodOn(VendedorControllerV2.class).getAll()).withRel("Vendedor"));
    }
}
