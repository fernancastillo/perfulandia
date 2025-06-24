package com.pagina.perfulandia.Sucursal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Sucursal.model.Sucursal;
import com.pagina.perfulandia.Sucursal.service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/sucursales")
@Tag(name = "Sucursal", description = "CRUD de las sucursales de Perfulandia")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;
    
    @GetMapping
    @Operation(summary = "Obtener todas las sucursales", description =  "Obtiene una lista de todas las sucursales de la base de datos de Perfulandia")
    public List<Sucursal> listarSucursal(){
        return sucursalService.getSucursales();
    }

    @PostMapping
    @Operation(summary = "Agregar una sucursal", description =  "Agrega una sucursal a la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Sucursal agregada exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Sucursal ya existe")
    }) 
    public Sucursal agregarSucursal(@RequestBody Sucursal sucursal){
        return sucursalService.saveSucursal(sucursal);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar una sucursal", description =  "Busca una sucursal en la base de datos de Perfulandia por su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Sucursal encontrada exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    }) 
    public Sucursal buscarSucursal(@PathVariable int id){
        return sucursalService.getSucursalId(id);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una sucursal", description =  "Actualiza una sucursal en la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Sucursal actualizada exitosamente",  
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Sucursal.class))),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    }) 
    public Sucursal actualizarSucursal(@PathVariable int id, @RequestBody Sucursal sucursal){
        sucursal.setId(id);
        return sucursalService.updateSucursal(sucursal);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sucursal", description =  "Elimina una sucursal de la base de datos de Perfulandia con su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Sucursal eliminada exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    }) 
    public String eliminarSucursal(@PathVariable int id){
        return sucursalService.deleteSucursal(id);
    }



}
