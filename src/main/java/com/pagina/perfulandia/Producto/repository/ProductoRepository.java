package com.pagina.perfulandia.Producto.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.pagina.perfulandia.Producto.model.Producto;

@Repository
public class ProductoRepository {
    private List<Producto> listaProductos = new ArrayList<>();

    public List<Producto> obtenerProductos(){
        return listaProductos;
    }

    public Producto buscarPorId(int id){
       for (Producto p : listaProductos) {
            if (p.getId() == id){
                return p;
            }
       } 
       return null;
    }

    public Producto guardarProducto(Producto p){
        listaProductos.add(p);
        return p;
    }

    public Producto actualizarProducto(Producto p){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == p.getId()) {
                id = p.getId();
                idPosicion = i;
            }
        }
        Producto producto1 = new Producto();
        producto1.setId(id);
        producto1.setNombre(p.getNombre());
        producto1.setDescripcion(p.getDescripcion());
        producto1.setPrecio(p.getPrecio());
        producto1.setStock(p.getStock());
        producto1.setCategoria(p.getCategoria());

        listaProductos.set(idPosicion, producto1);
        return producto1;
    }

    public void eliminarProducto(int id){
        Producto producto = buscarPorId(id);
        if (producto != null) {
            listaProductos.remove(producto);
        }
    }

}
