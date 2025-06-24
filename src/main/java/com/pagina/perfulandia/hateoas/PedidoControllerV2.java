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

import com.pagina.perfulandia.Pedido.model.Pedido;
import com.pagina.perfulandia.Pedido.repository.PedidoRepository;
import com.pagina.perfulandia.assemblers.PedidoModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/pedidos")
@Tag(name="Pedido HATEOAS", description = "CRUD de los pedidos de Perfulandia mediante HATEOAS")
public class PedidoControllerV2 {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Pedido>> getAll() {
        List<EntityModel<Pedido>> pedidos = pedidoRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pedidos,
                linkTo(methodOn(PedidoControllerV2.class).getAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pedido>> getPedidoById(@PathVariable int id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(pedido));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pedido>> createPedido(@RequestBody Pedido pedido) {
        Pedido newpedido = pedidoRepository.save(pedido);
        return ResponseEntity
                .created(linkTo(methodOn(PedidoControllerV2.class).getPedidoById(newpedido.getId())).toUri())
                .body(assembler.toModel(newpedido));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pedido>> updatePedido(@PathVariable int id, @RequestBody Pedido pedido) {
        Pedido existingPedido = pedidoRepository.findById(id).orElse(null);
        if (existingPedido == null) {
            return ResponseEntity.notFound().build();
        }
        pedido.setId(id);
        Pedido updatedPedido = pedidoRepository.save(pedido);
        return ResponseEntity
                .ok(assembler.toModel(updatedPedido));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deletePedido(@PathVariable int id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            return ResponseEntity.notFound().build(); 
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
