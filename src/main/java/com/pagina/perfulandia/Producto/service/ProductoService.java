package com.pagina.perfulandia.Producto.service;
import java.util.List;
import java.util.Optional;

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
        if (producto.getId() != 0) {
        throw new IllegalArgumentException("No se debe enviar un ID al crear un nuevo producto");
    }
        return productoRepository.save(producto);
    }

    public Producto getProductoId(int id){
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Producto updateProducto (Producto producto){
        Optional<Producto> existente = productoRepository.findById(producto.getId());
        if (!existente.isPresent()) {
        throw new RuntimeException("Producto no encontrado");
    }
        return productoRepository.save(producto);
    }

    public String deleteProducto(int id){
        Optional<Producto> existente = productoRepository.findById(id);
        if (!existente.isPresent()) {
        throw new RuntimeException("Producto con ID " + id + " no encontrado");
    }
        productoRepository.deleteById(id);
        return "Producto eliminado";
    }
}
