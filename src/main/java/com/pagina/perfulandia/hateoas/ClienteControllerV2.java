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

import com.pagina.perfulandia.Cliente.model.Cliente;
import com.pagina.perfulandia.Cliente.repository.ClienteRepository;
import com.pagina.perfulandia.assemblers.ClienteModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v2/clientes")
@Tag(name="Cliente HATEOAS", description = "CRUD de los clientes de Perfulandia mediante HATEOAS")
public class ClienteControllerV2 {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Cliente>> getAll() {
        List<EntityModel<Cliente>> clientes = clienteRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clientes,
                linkTo(methodOn(ClienteControllerV2.class).getAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> getClienteById(@PathVariable int id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(cliente));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> createCliente(@RequestBody Cliente cliente) {
        Cliente newcliente = clienteRepository.save(cliente);
        return ResponseEntity
                .created(linkTo(methodOn(ClienteControllerV2.class).getClienteById(newcliente.getId())).toUri())
                .body(assembler.toModel(newcliente));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> updateCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id).orElse(null);
        if (existingCliente == null) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        Cliente updatedCliente = clienteRepository.save(cliente);
        return ResponseEntity
                .ok(assembler.toModel(updatedCliente));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCliente(@PathVariable int id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            return ResponseEntity.notFound().build(); 
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
