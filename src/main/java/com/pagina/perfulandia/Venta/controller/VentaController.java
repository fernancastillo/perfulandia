package com.pagina.perfulandia.Venta.controller;


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

import com.pagina.perfulandia.Venta.model.Venta;
import com.pagina.perfulandia.Venta.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/ventas")
@Tag(name = "Venta", description = "CRUD de las ventas de Perfulandia")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    @Operation(summary = "Obtener todas las ventas", description =  "Obtiene una lista de todas las ventas de la base de datos de Perfulandia")
    public List<Venta> listarVentas(){
        return ventaService.getVentas();
    }

    @PostMapping
    @Operation(summary = "Agregar una venta", description =  "Agrega una venta a la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Venta agregada exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Venta ya existe")
    }) 
    public Venta agregarVenta(@RequestBody Venta venta){
        return ventaService.saveVenta(venta);    
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar una venta", description =  "Busca una venta en la base de datos de Perfulandia por su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Venta encontrada exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    }) 
    public Venta buscaVenta(@PathVariable int id){
        return ventaService.getVentaId(id);
    }
    
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una venta", description =  "Actualiza una venta en la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Venta actualizada exitosamente",  
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Venta.class))),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    }) 
    public Venta actualizaVenta(@PathVariable int id, @RequestBody Venta venta){
        venta.setId(id);
        return ventaService.updateVenta(venta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una venta", description =  "Elimina una venta de la base de datos de Perfulandia con su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Venta eliminada exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    }) 
    public String eliminarVenta(@PathVariable int id){
        return ventaService.deleteVenta(id);
    }

}
