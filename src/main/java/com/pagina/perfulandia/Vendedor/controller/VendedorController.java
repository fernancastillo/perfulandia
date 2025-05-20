package com.pagina.perfulandia.Vendedor.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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



@RestController
@RequestMapping("/api/v1/vendedores")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public List<Vendedor> listarVendedor(){
        return vendedorService.getVendedores();
    }

    @PostMapping
    public Vendedor agregarVendedor(@RequestBody Vendedor vendedor){
        return vendedorService.saveVendedor(vendedor);    
    }

    @GetMapping("{id}")
    public Vendedor buscaVendedor(@PathVariable int id){
        return vendedorService.getVendedorId(id);
    }
    
    @PutMapping("{id}")
    public Vendedor actualizaVendedor(@PathVariable int id, @RequestBody Vendedor vendedor){
        return vendedorService.updateVendedor(vendedor);
    }
    
    @DeleteMapping("{id}")
    public String eliminarVendedor(@PathVariable int id){
        return vendedorService.deleteVendedor(id);
    }


}