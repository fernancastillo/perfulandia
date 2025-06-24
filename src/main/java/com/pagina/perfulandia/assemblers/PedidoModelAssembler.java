package com.pagina.perfulandia.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.pagina.perfulandia.Pedido.model.Pedido;
import com.pagina.perfulandia.hateoas.PedidoControllerV2;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(PedidoControllerV2.class).getPedidoById(entity.getId())).withSelfRel(),
                linkTo(methodOn(PedidoControllerV2.class).getAll()).withRel("Pedido"));
    }

}
