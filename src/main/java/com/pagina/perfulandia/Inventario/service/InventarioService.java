package com.pagina.perfulandia.Inventario.service;
import java.util.List;
import java.util.Optional;

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
        if (inventario.getId() != 0) {
        throw new IllegalArgumentException("No se debe enviar un ID al crear un nuevo inventario");
    }
        return inventarioRepository.save(inventario);
    }

    public Inventario getInventarioId(int id){
        return inventarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));
    }

    public Inventario updateInventario(Inventario inventario){
        Optional<Inventario> existente = inventarioRepository.findById(inventario.getId());
        if (!existente.isPresent()) {
        throw new RuntimeException("Inventario no encontrado");
    }
        return inventarioRepository.save(inventario);
    }

    public String deleteInventario(int id){
        Optional<Inventario> existente = inventarioRepository.findById(id);
        if (!existente.isPresent()) {
        throw new RuntimeException("Inventario con ID " + id + " no encontrado");
    }
        inventarioRepository.deleteById(id);
        return "Inventario Eliminado";
    }
}