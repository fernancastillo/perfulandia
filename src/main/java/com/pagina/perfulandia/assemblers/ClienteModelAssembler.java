package com.pagina.perfulandia.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.pagina.perfulandia.Cliente.model.Cliente;
import com.pagina.perfulandia.hateoas.ClienteControllerV2;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ClienteControllerV2.class).getClienteById(entity.getId())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).getAll()).withRel("Cliente"));
    }

}