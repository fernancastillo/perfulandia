package com.pagina.perfulandia.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.pagina.perfulandia.Producto.model.Producto;
import com.pagina.perfulandia.hateoas.ProductoControllerV2;



@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ProductoControllerV2.class).getProductoById(entity.getId())).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).getAll()).withRel("Producto"));
    }

}
