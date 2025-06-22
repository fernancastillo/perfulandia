package com.pagina.perfulandia.Cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.pagina.perfulandia.Cliente.model.Cliente;
import com.pagina.perfulandia.Cliente.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Cliente", description = "CRUD de los clientes de Perfulandia")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description =  "Obtiene una lista de todos los clientes de la base de datos de Perfulandia")
    public List<Cliente> listarClientes(){
        return clienteService.getClientes();
    }

    @PostMapping
    @Operation(summary = "Agregar un cliente", description =  "Agrega un cliente a la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Cliente agregado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Cliente ya existe")
    }) 
    public Cliente agregarCliente(@RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);    
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un cliente", description =  "Busca un cliente en la base de datos de Perfulandia por su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    }) 
    public Cliente buscarCliente(@PathVariable int id){
        return clienteService.getClienteId(id);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente", description =  "Actualiza un cliente en la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente",  
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    }) 
    public Cliente actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente){
        return clienteService.updateCliente(cliente);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente", description =  "Elimina un cliente de la base de datos de Perfulandia con su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    }) 
    public String eliminarCliente(@PathVariable int id){
        return clienteService.deleteCliente(id);
    }


}
