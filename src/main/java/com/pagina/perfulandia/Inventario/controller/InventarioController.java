package com.pagina.perfulandia.Inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Inventario.model.Inventario;
import com.pagina.perfulandia.Inventario.service.InventarioService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/productos")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Inventario> listarInventario(){
        return inventarioService.getInventarios();
        
    }

    @PostMapping
    public Inventario agregarInventario(@RequestBody Inventario inventario){
        return inventarioService.saveInventario(inventario);
    }

    @GetMapping("{id}")
    public Inventario buscarInventario(@PathVariable int id){
        return inventarioService.getInventarioId(id);
    }
    
    @PutMapping("{id}")
    public Inventario actualizarInventario(@PathVariable int id, @RequestBody Inventario inventario){
        return inventarioService.updateInventario(inventario);
    }

    @DeleteMapping("{id}")
    public String eliminarInventario(@PathVariable int id){
        return inventarioService.deleteInventario(id);
    }
}
