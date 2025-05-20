package com.pagina.perfulandia.Gerente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.GetMapping;

import com.pagina.perfulandia.Gerente.model.Gerente;
import com.pagina.perfulandia.Gerente.service.GerenteService;



@RestController
@RequestMapping("/api/v1/gerentes")
public class GerenteController {
    @Autowired
    private GerenteService gerenteService;

    @GetMapping
    public List<Gerente> listarGerentes(){
        return gerenteService.getGerentes();
    }

    @PostMapping
    public Gerente agregarGerente(@RequestBody Gerente gerente){
        return gerenteService.saveGerente(gerente);    
    }

    @GetMapping("{id}")
    public Gerente buscarGerente(@PathVariable int id){
        return gerenteService.getGerenteId(id);
    }
    
    @PutMapping("{id}")
    public Gerente actualizarGerente(@PathVariable int id, @RequestBody Gerente gerente){
        return gerenteService.updateGerente(gerente);
    }
    
    @DeleteMapping("{id}")
    public String eliminarProducto(@PathVariable int id){
        return gerenteService.deleteGerente(id);
    }
    
}