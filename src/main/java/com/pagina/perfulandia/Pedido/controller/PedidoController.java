package com.pagina.perfulandia.Pedido.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pagina.perfulandia.Pedido.model.Pedido;
import com.pagina.perfulandia.Pedido.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedido", description = "CRUD de los pedidos de Perfulandia")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description =  "Obtiene una lista de todos los pedidos de la base de datos de Perfulandia")
    public List<Pedido> listarPedidos(){
        return pedidoService.getPedidos();
    }

    @PostMapping
    @Operation(summary = "Agregar un pedido", description =  "Agrega un pedido a la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Pedido agregado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Pedido ya existe")
    }) 
    public Pedido agregarPedido(@RequestBody Pedido pedido){
        return pedidoService.savePedido(pedido);    
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un pedido", description =  "Busca un pedido en la base de datos de Perfulandia por su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    }) 
    public Pedido buscaPedido(@PathVariable int id){
        return pedidoService.getPedidoId(id);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pedido", description =  "Actualiza un pedido en la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Pedido actualizado exitosamente",  
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    }) 
    public Pedido actualizaPedido(@PathVariable int id, @RequestBody Pedido pedido){
        pedido.setId(id);
        return pedidoService.updatePedido(pedido);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pedido", description =  "Elimina un pedido de la base de datos de Perfulandia con su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Pedido eliminado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    }) 
    public String eliminarPedido(@PathVariable int id){
        return pedidoService.deletePedido(id);
    }


}
