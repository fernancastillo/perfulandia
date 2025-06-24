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

import com.pagina.perfulandia.Inventario.model.Inventario;
import com.pagina.perfulandia.Inventario.repository.InventarioRepository;
import com.pagina.perfulandia.assemblers.InventarioModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/inventarios")
@Tag(name="Inventario HATEOAS", description = "CRUD de los inventarios de Perfulandia mediante HATEOAS")
public class InventarioControllerV2 {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private InventarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Inventario>> getAll() {
        List<EntityModel<Inventario>> inventarios = inventarioRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventarios,
                linkTo(methodOn(InventarioControllerV2.class).getAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> getInventarioById(@PathVariable int id) {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);
        if (inventario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(inventario));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> createInventario(@RequestBody Inventario inventario) {
        Inventario newinventario = inventarioRepository.save(inventario);
        return ResponseEntity
                .created(linkTo(methodOn(InventarioControllerV2.class).getInventarioById(newinventario.getId())).toUri())
                .body(assembler.toModel(newinventario));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> updateinventario(@PathVariable int id, @RequestBody Inventario inventario) {
        Inventario existingInventario = inventarioRepository.findById(id).orElse(null);
        if (existingInventario == null) {
            return ResponseEntity.notFound().build();
        }
        inventario.setId(id);
        Inventario updatedInventario = inventarioRepository.save(inventario);
        return ResponseEntity
                .ok(assembler.toModel(updatedInventario));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteInventario(@PathVariable int id) {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);
        if (inventario == null) {
            return ResponseEntity.notFound().build(); 
        }
        inventarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

