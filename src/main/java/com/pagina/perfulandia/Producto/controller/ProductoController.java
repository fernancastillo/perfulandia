package com.pagina.perfulandia.Producto.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Producto.model.Producto;
import com.pagina.perfulandia.Producto.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProductos(){
        return productoService.geProductos();
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto){
        return productoService.saveProducto(producto);    
    }

    @GetMapping("{id}")
    public Producto buscaProducto(@PathVariable int id){
        return productoService.geProductoId(id);
    }
    
    @PutMapping("{id}")
    public Producto actualizaProducto(@PathVariable int id, @RequestBody Producto producto){
        return productoService.updateProducto(producto);
    }
    
    @DeleteMapping("{id}")
    public String eliminarProducto(@PathVariable int id){
        return productoService.deleteProducto(id);
    }


}
