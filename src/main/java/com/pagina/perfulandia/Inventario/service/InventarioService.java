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
        return inventarioRepository.findAll();
    }

    public Inventario saveInventario(Inventario inventario){
        return inventarioRepository.save(inventario);
    }

    public Inventario getInventarioId(int id){
        return inventarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));
    }

    public Inventario updateInventario(Inventario iventario){
        return inventarioRepository.save(iventario);
    }

    public String deleteInventario(int id){
        inventarioRepository.deleteById(id);
        return "Inventario Eliminado";
    }
}