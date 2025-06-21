package com.pagina.perfulandia.Inventario.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Inventario.model.Inventario;
import com.pagina.perfulandia.Inventario.repository.InventarioRepository;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> getInventarios(){
        return inventarioRepository.obtenerInventarios();
    }

    public Inventario saveInventario(Inventario inventario){
        return inventarioRepository.guardarInventario(inventario);
    }

    public Inventario getInventarioId(int id){
        return inventarioRepository.buscarPorId(id);
    }

    public Inventario updateInventario(Inventario iventario){
        return inventarioRepository.actualizarInventario(iventario);
    }

    public String deleteInventario(int id){
        inventarioRepository.eliminarInventario(id);
        return "Producto Eliminado";
    }
}