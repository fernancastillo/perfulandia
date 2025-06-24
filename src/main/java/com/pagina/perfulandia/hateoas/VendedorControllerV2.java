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

import com.pagina.perfulandia.Vendedor.model.Vendedor;
import com.pagina.perfulandia.Vendedor.repository.VendedorRepository;
import com.pagina.perfulandia.assemblers.VendedorModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/vendedores")
@Tag(name="Vendedor HATEOAS", description = "CRUD de los vendedores de Perfulandia mediante HATEOAS")
public class VendedorControllerV2 {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private VendedorModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Vendedor>> getAll() {
        List<EntityModel<Vendedor>> vendedores = vendedorRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(vendedores,
                linkTo(methodOn(VendedorControllerV2.class).getAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Vendedor>> getVendedorById(@PathVariable int id) {
        Vendedor vendedor = vendedorRepository.findById(id).orElse(null);
        if (vendedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(vendedor));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Vendedor>> createVendedor(@RequestBody Vendedor vendedor) {
        Vendedor newvendedor = vendedorRepository.save(vendedor);
        return ResponseEntity
                .created(linkTo(methodOn(VendedorControllerV2.class).getVendedorById(newvendedor.getId())).toUri())
                .body(assembler.toModel(newvendedor));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Vendedor>> updateVendedor(@PathVariable int id, @RequestBody Vendedor vendedor) {
        Vendedor existingVendedor = vendedorRepository.findById(id).orElse(null);
        if (existingVendedor == null) {
            return ResponseEntity.notFound().build();
        }
        vendedor.setId(id);
        Vendedor updatedVendedor = vendedorRepository.save(vendedor);
        return ResponseEntity
                .ok(assembler.toModel(updatedVendedor));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteVendedor(@PathVariable int id) {
        Vendedor vendedor = vendedorRepository.findById(id).orElse(null);
        if (vendedor == null) {
            return ResponseEntity.notFound().build(); 
        }
        vendedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
