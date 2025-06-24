package com.pagina.perfulandia.Proveedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Proveedor.model.Proveedor;
import com.pagina.perfulandia.Proveedor.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/proveedores")
@Tag(name = "Proveedor", description = "CRUD de los proveedores de Perfulandia")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores", description =  "Obtiene una lista de todos los proveedores de la base de datos de Perfulandia")
    public List<Proveedor> listarProveedores(){
        return proveedorService.getProveedores();
    }

    @PostMapping
    @Operation(summary = "Agregar un proveedor", description =  "Agrega un proveedor a la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Proveedor agregado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Proveedor ya existe")
    })
    public Proveedor agregarProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.saveProveedor(proveedor);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un proveedor", description =  "Busca un proveedor en la base de datos de Perfulandia por su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Proveedor encontrado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    }) 
    public Proveedor buscarProveedor(@PathVariable int id){
        return proveedorService.getProveedorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proveedor", description =  "Actualiza un proveedor en la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Proveedor actualizado exitosamente",  
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Proveedor.class))),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    }) 
    public Proveedor actualizarProveedor(@PathVariable int id, @RequestBody Proveedor proveedor){
        proveedor.setId(id);
        return proveedorService.updateProveedor(proveedor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proveedor", description =  "Elimina un proveedor de la base de datos de Perfulandia con su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Proveedor eliminado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    }) 
    public String eliminarProveedor(@PathVariable int id){
        return proveedorService.deleteProveedor(id);
    }

}
