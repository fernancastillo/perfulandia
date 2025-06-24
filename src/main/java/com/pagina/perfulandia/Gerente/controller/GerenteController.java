package com.pagina.perfulandia.Gerente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Gerente.model.Gerente;
import com.pagina.perfulandia.Gerente.service.GerenteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/gerentes")
@Tag(name = "Gerente", description = "CRUD de los gerentes de Perfulandia")
public class GerenteController {
    @Autowired
    private GerenteService gerenteService;

    @GetMapping
    @Operation(summary = "Obtener todos los gerentes", description =  "Obtiene una lista de todos los gerentes de la base de datos de Perfulandia")
    public List<Gerente> listarGerentes(){
        return gerenteService.getGerentes();
    }

    @PostMapping
    @Operation(summary = "Agregar un gerente", description =  "Agrega un gerente a la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Gerente agregado exitosamente"),  
        @ApiResponse(responseCode = "201", description = "Gerente agregado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Gerente ya existe")
    })
    public ResponseEntity<Gerente> agregarGerente(@RequestBody Gerente gerente){
        Gerente nuevoGerente = gerenteService.saveGerente(gerente);    
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGerente);   
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un gerente", description =  "Busca un gerente en la base de datos de Perfulandia por su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Gerente encontrado exitosamente"),  
        @ApiResponse(responseCode = "201", description = "Gerente encontrado exitosamente"), 
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    }) 
    public Gerente buscarGerente(@PathVariable int id){
        return gerenteService.getGerenteId(id);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un gerente", description =  "Actualiza un gerente en la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Gerente actualizado exitosamente",  
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Gerente.class))),
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    }) 
    public Gerente actualizarGerente(@PathVariable int id, @RequestBody Gerente gerente){
        gerente.setId(id);
        return gerenteService.updateGerente(gerente);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un gerente", description =  "Elimina un gerente de la base de datos de Perfulandia con su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Gerente eliminado exitosamente"),  
        @ApiResponse(responseCode = "201", description = "Gerente eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    }) 
    public ResponseEntity<String> eliminarProducto(@PathVariable int id){
        String mensaje = gerenteService.deleteGerente(id);
        return ResponseEntity.ok(mensaje);
    }
    
}