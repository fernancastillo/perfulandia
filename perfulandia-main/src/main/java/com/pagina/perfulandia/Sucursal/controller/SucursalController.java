package com.pagina.perfulandia.Sucursal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Sucursal.model.Sucursal;
import com.pagina.perfulandia.Sucursal.service.SucursalService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;
    
    @GetMapping
    public List<Sucursal> listarSucursal(){
        return sucursalService.getSucursales();
    }

    @PostMapping
    public Sucursal agregarSucursal(@RequestBody Sucursal sucursal){
        return sucursalService.saveSucursal(sucursal);
    }

    @GetMapping("{id}")
    public Sucursal buscarSucursal(@PathVariable int id){
        return sucursalService.getSucursalId(id);
    }
    
    @PutMapping("{id}")
    public Sucursal actualizarSucursal(@PathVariable int id, @RequestBody Sucursal sucursal){
        return sucursalService.updateSucursal(sucursal);
    }

    @DeleteMapping("{id}")
    public String eliminarSucursal(@PathVariable int id){
        return sucursalService.deleteSucursal(id);
    }



}
