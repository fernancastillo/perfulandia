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

import com.pagina.perfulandia.Venta.model.Venta;
import com.pagina.perfulandia.Venta.repository.VentaRepository;
import com.pagina.perfulandia.assemblers.VentaModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/ventas")
@Tag(name="Venta HATEOAS", description = "CRUD de las ventas de Perfulandia mediante HATEOAS")
public class VentaControllerV2 {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private VentaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Venta>> getAll() {
        List<EntityModel<Venta>> ventas = ventaRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(ventas,
                linkTo(methodOn(VentaControllerV2.class).getAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> getVentaById(@PathVariable int id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(venta));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> createVenta(@RequestBody Venta venta) {
        Venta newventa = ventaRepository.save(venta);
        return ResponseEntity
                .created(linkTo(methodOn(VentaControllerV2.class).getVentaById(newventa.getId())).toUri())
                .body(assembler.toModel(newventa));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> updateVenta(@PathVariable int id, @RequestBody Venta venta) {
        Venta existingVenta = ventaRepository.findById(id).orElse(null);
        if (existingVenta == null) {
            return ResponseEntity.notFound().build();
        }
        venta.setId(id);
        Venta updatedVenta = ventaRepository.save(venta);
        return ResponseEntity
                .ok(assembler.toModel(updatedVenta));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteVenta(@PathVariable int id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) {
            return ResponseEntity.notFound().build(); 
        }
        ventaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
