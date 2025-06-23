package com.pagina.perfulandia.hateoas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Administrador.model.Administrador;
import com.pagina.perfulandia.Administrador.repository.AdministradorRepository;
import com.pagina.perfulandia.assemblers.AdministradorModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/administradores")
@Tag(name="Administrador", description = "CRUD de los administradores")
public class AdministradorControllerV2 {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private AdministradorModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Administrador>> getAll() {
        List<EntityModel<Administrador>> administradores = administradorRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(administradores,
                linkTo(methodOn(AdministradorControllerV2.class).getAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Administrador> getAdministradorById(@PathVariable int id) {
        Administrador administrador = administradorRepository.getById(id);
        return assembler.toModel(administrador);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Administrador>> createAdministrador(@RequestBody Administrador administrador) {
        Administrador newadministrador = administradorRepository.save(administrador);
        return ResponseEntity
                .created(linkTo(methodOn(AdministradorControllerV2.class).getAdministradorById(newadministrador.getId())).toUri())
                .body(assembler.toModel(newadministrador));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Administrador>> updateAdministrador(@PathVariable int id, @RequestBody Administrador administrador) {
        administrador.setId(id);
        Administrador updatedAdministrador = administradorRepository.save(administrador);
        return ResponseEntity
                .ok(assembler.toModel(updatedAdministrador));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteAdministrador(@PathVariable int id) {
        administradorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
