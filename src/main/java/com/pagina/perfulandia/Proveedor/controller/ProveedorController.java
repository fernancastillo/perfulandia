package com.pagina.perfulandia.Proveedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Proveedor.model.Proveedor;
import com.pagina.perfulandia.Proveedor.service.ProveedorService;


@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> listarProveedores(){
        return proveedorService.getProveedor();
    }

    @PostMapping
    public Proveedor agregarProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.saveProveedor(proveedor);
    }

    @GetMapping("{id}")
    public Proveedor buscarProveedor(@PathVariable int id){
        return proveedorService.getProveedorId(id);
    }

    @PutMapping("{id}")
    public Proveedor actualizarProveedor(@PathVariable int id, @RequestBody Proveedor proveedor){
        return proveedorService.updatProveedor(proveedor);
    }

    @DeleteMapping("{id}")
    public String eliminarProveedor(@PathVariable int id){
        return proveedorService.deleteProveedor(id);
    }

}
