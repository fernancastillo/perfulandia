package com.pagina.perfulandia.Administrador.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Administrador.model.Administrador;
import com.pagina.perfulandia.Administrador.service.AdministradorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;;

@RestController
@RequestMapping("/api/v1/administradores")
@Tag(name = "Administradores", description = "Operaciones relacionadas con los administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    @Operation(summary = "Obtener todos los administradores", description = "Obtiene una lista de todos los administradores")
    public List<Administrador> listarAdministradores(){
        return administradorService.getAdministradores();
    }

    @PostMapping
    @Operation(summary = "Agregar un administrador", description = "Se agrega un administrador")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador agregado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Administrador.class))),
            @ApiResponse(responseCode = "400", description = "Administrador ya existe")
    })
    public Administrador agregarAdministrador(@RequestBody Administrador administrador){
        return administradorService.saveAdministrador(administrador);    
    }

    @GetMapping("{id}")
        @Operation(summary = "Buscar un administrador", description = "Se busca un administrador por su ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Administrador.class))),
            @ApiResponse(responseCode = "400", description = "Administrador no existe")
    })
    public Administrador buscaAdministrador(@PathVariable int id){
        return administradorService.getAdministradorId(id);
    }
    


    @PutMapping("{id}")
    @Operation(summary = "Actualizar un administrador", description = "Actualiza un administrador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Administrador.class))),
            @ApiResponse(responseCode = "400", description = "Administrador no encontrado")
    })
    public Administrador actualizaAdministrador(@PathVariable int id, @RequestBody Administrador administrador){
        return administradorService.updateAdministrador(administrador);
    }
    
    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar un administrador", description = "Elimina un administrador por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Administrador no encontrado")
    })
    public String eliminarAdministrador(@PathVariable int id){
        return administradorService.deleteAdministrador(id);
    }


}