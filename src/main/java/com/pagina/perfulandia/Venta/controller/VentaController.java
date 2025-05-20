package com.pagina.perfulandia.Venta.controller;


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

import com.pagina.perfulandia.Venta.model.Venta;
import com.pagina.perfulandia.Venta.service.VentaService;



@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> listarVentas(){
        return ventaService.getVentas();
    }

    @PostMapping
    public Venta agregarVenta(@RequestBody Venta venta){
        return ventaService.saveVenta(venta);    
    }

   @GetMapping("{id}")
    public Venta buscaVenta(@PathVariable int id){
        return ventaService.getVentaId(id);
    }
    
    
    @PutMapping("{id}")
    public Venta actualizaVenta(@PathVariable int id, @RequestBody Venta venta){
        return ventaService.updateVenta(venta);
    }

     @DeleteMapping("{id}")
    public String eliminarVenta(@PathVariable int id){
        return ventaService.deleteVenta(id);
    }


}
