package com.pagina.perfulandia.hateoas;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagina.perfulandia.Administrador.model.Administrador;
import com.pagina.perfulandia.Administrador.repository.AdministradorRepository;
import com.pagina.perfulandia.assemblers.AdministradorModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/administradores")
@Tag(name="Administrador HATEOAS", description = "CRUD de los administradores de Perfulandia mediante HATEOAS")
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
    public ResponseEntity<EntityModel<Administrador>> getAdministradorById(@PathVariable int id) {
        Administrador administrador = administradorRepository.findById(id).orElse(null);
        if (administrador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(administrador));
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
        Administrador existingAdministrador = administradorRepository.findById(id).orElse(null);
        if (existingAdministrador == null) {
            return ResponseEntity.notFound().build();
        }
        administrador.setId(id);
        Administrador updatedAdministrador = administradorRepository.save(administrador);
        return ResponseEntity
                .ok(assembler.toModel(updatedAdministrador));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteAdministrador(@PathVariable int id) {
        Administrador administrador = administradorRepository.findById(id).orElse(null);
        if (administrador == null) {
            return ResponseEntity.notFound().build(); 
        }
        administradorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
