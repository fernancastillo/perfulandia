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


import com.pagina.perfulandia.Proveedor.model.Proveedor;
import com.pagina.perfulandia.Proveedor.repository.ProveedorRepository;
import com.pagina.perfulandia.assemblers.ProveedorModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/proveedores")
@Tag(name="Proveedor HATEOAS", description = "CRUD de los proveedores de Perfulandia mediante HATEOAS")
public class ProveedorControllerV2 {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Proveedor>> getAll() {
        List<EntityModel<Proveedor>> proveedores = proveedorRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(proveedores,
                linkTo(methodOn(ProveedorControllerV2.class).getAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Proveedor>> getProveedorById(@PathVariable int id) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(proveedor));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Proveedor>> createProveedor(@RequestBody Proveedor proveedor) {
        Proveedor newproveedor = proveedorRepository.save(proveedor);
        return ResponseEntity
                .created(linkTo(methodOn(ProveedorControllerV2.class).getProveedorById(newproveedor.getId())).toUri())
                .body(assembler.toModel(newproveedor));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Proveedor>> updateProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        Proveedor existingProveedor = proveedorRepository.findById(id).orElse(null);
        if (existingProveedor == null) {
            return ResponseEntity.notFound().build();
        }
        proveedor.setId(id);
        Proveedor updatedProveedor = proveedorRepository.save(proveedor);
        return ResponseEntity
                .ok(assembler.toModel(updatedProveedor));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteProveedor(@PathVariable int id) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor == null) {
            return ResponseEntity.notFound().build(); 
        }
        proveedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
