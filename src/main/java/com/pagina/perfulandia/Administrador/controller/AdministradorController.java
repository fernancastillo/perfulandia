package com.pagina.perfulandia.Administrador.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Administrador.model.Administrador;
import com.pagina.perfulandia.Administrador.service.AdministradorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/administradores")
@Tag(name = "Administrador", description = "CRUD de los administradores de Perfulandia")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    @Operation(summary = "Obtener todos los administradores", description =  "Obtiene una lista de todos los administradores de la base de datos de Perfulandia")
    public List<Administrador> listarAdministradores(){
        return administradorService.getAdministradores();
    }

    @PostMapping
    @Operation(summary = "Agregar un administrador", description =  "Agrega un administrador a la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Administrador agregado exitosamente"),
        @ApiResponse(responseCode = "201", description = "Administrador agregado exitosamente"),   
        @ApiResponse(responseCode = "404", description = "Administrador ya existe")
    }) 
    public ResponseEntity<Administrador> agregarAdministrador(@RequestBody Administrador administrador){
        Administrador nuevoAdministrador = administradorService.saveAdministrador(administrador);    
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdministrador);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un administrador", description =  "Busca un administrador en la base de datos de Perfulandia por su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Administrador encontrado exitosamente"),
        @ApiResponse(responseCode = "201", description = "Administrador encontrado exitosamente"),   
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    }) 
    public Administrador buscaAdministrador(@PathVariable int id){
        return administradorService.getAdministradorId(id);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un administrador", description =  "Actualiza un administrador en la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente",  
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Administrador.class))),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    }) 
    public Administrador actualizaAdministrador(@PathVariable int id, @RequestBody Administrador administrador){
        administrador.setId(id);
        return administradorService.updateAdministrador(administrador);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un administrador", description =  "Elimina un administrador de la base de datos de Perfulandia con su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Administrador eliminado exitosamente"),  
        @ApiResponse(responseCode = "201", description = "Administrador eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    }) 
    public ResponseEntity<String> eliminarAdministrador(@PathVariable int id){
        String mensaje = administradorService.deleteAdministrador(id);
        return ResponseEntity.ok(mensaje);
    }

}