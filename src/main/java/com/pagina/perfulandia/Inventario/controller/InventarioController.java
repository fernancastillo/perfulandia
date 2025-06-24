package com.pagina.perfulandia.Inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.pagina.perfulandia.Inventario.model.Inventario;
import com.pagina.perfulandia.Inventario.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/inventarios")
@Tag(name = "Inventario", description = "CRUD de los inventarios de Perfulandia")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    @Operation(summary = "Obtener todos los inventarios", description =  "Obtiene una lista de todos los inventarios de la base de datos de Perfulandia")
    public List<Inventario> listarInventario(){
        return inventarioService.getInventarios();
        
    }

    @PostMapping
    @Operation(summary = "Agregar un inventario", description =  "Agrega un inventario a la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Inventario agregado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Inventario ya existe")
    }) 
    public Inventario agregarInventario(@RequestBody Inventario inventario){
        return inventarioService.saveInventario(inventario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un inventario", description =  "Busca un inventario en la base de datos de Perfulandia por su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Inventario encontrado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    }) 
    public Inventario buscarInventario(@PathVariable int id){
        return inventarioService.getInventarioId(id);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un inventario", description =  "Actualiza un inventario en la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Inventario actualizado exitosamente",  
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    }) 
    public Inventario actualizarInventario(@PathVariable int id, @RequestBody Inventario inventario){
        inventario.setId(id);
        return inventarioService.updateInventario(inventario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un inventario", description =  "Elimina un inventario de la base de datos de Perfulandia con su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Inventario eliminado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    }) 
    public String eliminarInventario(@PathVariable int id){
        return inventarioService.deleteInventario(id);
    }
}
