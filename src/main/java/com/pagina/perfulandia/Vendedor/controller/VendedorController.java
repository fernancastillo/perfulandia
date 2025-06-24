package com.pagina.perfulandia.Vendedor.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagina.perfulandia.Vendedor.model.Vendedor;
import com.pagina.perfulandia.Vendedor.service.VendedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/vendedores")
@Tag(name = "Vendedor", description = "CRUD de los vendedores de Perfulandia")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    @Operation(summary = "Obtener todos los vendedores", description =  "Obtiene una lista de todos los vendedores de la base de datos de Perfulandia")
    public List<Vendedor> listarVendedor(){
        return vendedorService.getVendedores();
    }

    @PostMapping
    @Operation(summary = "Agregar un vendedor", description =  "Agrega un vendedor a la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Vendedor agregado exitosamente"),  
        @ApiResponse(responseCode = "201", description = "Vendedor agregado exitosamente"),   
        @ApiResponse(responseCode = "404", description = "Vendedor ya existe")
    }) 
    public ResponseEntity<Vendedor> agregarVendedor(@RequestBody Vendedor vendedor){
        Vendedor nuevoVendedor = vendedorService.saveVendedor(vendedor);    
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVendedor);    
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un vendedor", description =  "Busca un vendedor en la base de datos de Perfulandia por su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Vendedor encontrado exitosamente"),  
        @ApiResponse(responseCode = "201", description = "Vendedor encontrado exitosamente"),   
        @ApiResponse(responseCode = "404", description = "Vendedor no encontrado")
    }) 
    public Vendedor buscaVendedor(@PathVariable int id){
        return vendedorService.getVendedorId(id);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un vendedor", description =  "Actualiza un vendedor en la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Vendedor actualizado exitosamente",  
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Vendedor.class))),
        @ApiResponse(responseCode = "404", description = "Vendedor no encontrado")
    }) 
    public Vendedor actualizaVendedor(@PathVariable int id, @RequestBody Vendedor vendedor){
        vendedor.setId(id);
        return vendedorService.updateVendedor(vendedor);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un vendedor", description =  "Elimina un vendedor de la base de datos de Perfulandia con su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Vendedor eliminado exitosamente"),  
        @ApiResponse(responseCode = "201", description = "Vendedor eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Vendedor no encontrado")
    }) 
    public ResponseEntity<String> eliminarVendedor(@PathVariable int id){
        String mensaje = vendedorService.deleteVendedor(id);
        return ResponseEntity.ok(mensaje);
    }


}