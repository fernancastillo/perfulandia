package com.pagina.perfulandia.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pagina.perfulandia.Administrador.model.Administrador;
import com.pagina.perfulandia.hateoas.AdministradorControllerV2;

@Component
public class AdministradorModelAssembler implements RepresentationModelAssembler<Administrador, EntityModel<Administrador>> {

    @Override
    public EntityModel<Administrador> toModel(Administrador entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AdministradorControllerV2.class).getAdministradorById(entity.getId())).withSelfRel(),
                linkTo(methodOn(AdministradorControllerV2.class).getAll()).withRel("Administrador"));
    }

}
