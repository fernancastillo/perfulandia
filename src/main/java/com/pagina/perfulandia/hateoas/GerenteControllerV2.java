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

import com.pagina.perfulandia.Gerente.model.Gerente;
import com.pagina.perfulandia.Gerente.repository.GerenteRepository;
import com.pagina.perfulandia.assemblers.GerenteModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/gerentes")
@Tag(name="Gerente HATEOAS", description = "CRUD de los gerentes de Perfulandia mediante HATEOAS")
public class GerenteControllerV2 {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private GerenteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Gerente>> getAll() {
        List<EntityModel<Gerente>> gerentes = gerenteRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(gerentes,
                linkTo(methodOn(GerenteControllerV2.class).getAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Gerente>> getGerenteById(@PathVariable int id) {
        Gerente gerente = gerenteRepository.findById(id).orElse(null);
        if (gerente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(gerente));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Gerente>> createGerente(@RequestBody Gerente gerente) {
        Gerente newgerente = gerenteRepository.save(gerente);
        return ResponseEntity
                .created(linkTo(methodOn(GerenteControllerV2.class).getGerenteById(newgerente.getId())).toUri())
                .body(assembler.toModel(newgerente));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Gerente>> updateGerente(@PathVariable int id, @RequestBody Gerente gerente) {
        Gerente existingGerente = gerenteRepository.findById(id).orElse(null);
        if (existingGerente == null) {
            return ResponseEntity.notFound().build();
        }
        gerente.setId(id);
        Gerente updatedGerente = gerenteRepository.save(gerente);
        return ResponseEntity
                .ok(assembler.toModel(updatedGerente));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteGerente(@PathVariable int id) {
        Gerente gerente = gerenteRepository.findById(id).orElse(null);
        if (gerente == null) {
            return ResponseEntity.notFound().build(); 
        }
        gerenteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

