package com.pagina.perfulandia.Producto.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Producto.model.Producto;
import com.pagina.perfulandia.Producto.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos(){
        return productoRepository.findAll();
    }

    public Producto saveProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto getProductoId(int id){
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Producto updateProducto (Producto producto){
        return productoRepository.save(producto);
    }

    public String deleteProducto(int id){
        productoRepository.deleteById(id);
        return "Producto eliminado";
    }
}
