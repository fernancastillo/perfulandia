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

import com.pagina.perfulandia.Sucursal.model.Sucursal;
import com.pagina.perfulandia.Sucursal.repository.SucursalRepository;
import com.pagina.perfulandia.assemblers.SucursalModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/sucursales")
@Tag(name="Sucursal HATEOAS", description = "CRUD de las sucursales de Perfulandia mediante HATEOAS")
public class SucursalControllerV2 {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private SucursalModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Sucursal>> getAll() {
        List<EntityModel<Sucursal>> sucursales = sucursalRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sucursales,
                linkTo(methodOn(SucursalControllerV2.class).getAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Sucursal>> getSucursalById(@PathVariable int id) {
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);
        if (sucursal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(sucursal));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Sucursal>> createSucursal(@RequestBody Sucursal sucursal) {
        Sucursal newsucursal = sucursalRepository.save(sucursal);
        return ResponseEntity
                .created(linkTo(methodOn(SucursalControllerV2.class).getSucursalById(newsucursal.getId())).toUri())
                .body(assembler.toModel(newsucursal));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Sucursal>> updateSucursal(@PathVariable int id, @RequestBody Sucursal sucursal) {
        Sucursal existingSucursal = sucursalRepository.findById(id).orElse(null);
        if (existingSucursal == null) {
            return ResponseEntity.notFound().build();
        }
        sucursal.setId(id);
        Sucursal updatedSucursal = sucursalRepository.save(sucursal);
        return ResponseEntity
                .ok(assembler.toModel(updatedSucursal));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteSucursal(@PathVariable int id) {
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);
        if (sucursal == null) {
            return ResponseEntity.notFound().build(); 
        }
        sucursalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
