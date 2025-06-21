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
        return productoRepository.obtenerProductos();
    }

    public Producto saveProducto(Producto producto){
        return productoRepository.guardarProducto(producto);
    }

    public Producto getProductoId(int id){
        return productoRepository.buscarPorId(id);
    }

    public Producto updateProducto (Producto producto){
        return productoRepository.actualizarProducto(producto);
    }

    public String deleteProducto(int id){
        productoRepository.eliminarProducto(id);
        return "Producto eliminado";
    }
}
