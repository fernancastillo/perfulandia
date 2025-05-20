package com.pagina.perfulandia.Administrador.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Administrador.model.Administrador;
import com.pagina.perfulandia.Administrador.service.AdministradorService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<Administrador> listarProductos(){
        return administradorService.getAdministradores();
    }

    @PostMapping
    public Administrador agregarAdministrador(@RequestBody Administrador administrador){
        return administradorService.saveAdministrador(administrador);    
    }

    @GetMapping("{id}")
    public Administrador buscaAdministrador(@PathVariable int id){
        return administradorService.getAdministradorId(id);
    }
    
    @PutMapping("{id}")
    public Administrador actualizaAdministrador(@PathVariable int id, @RequestBody Administrador administrador){
        return administradorService.updateAdministrador(administrador);
    }
    
    @DeleteMapping("{id}")
    public String eliminarAdministrador(@PathVariable int id){
        return administradorService.deleteAdministrador(id);
    }


}