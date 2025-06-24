package com.pagina.perfulandia.Proveedor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Proveedor.model.Proveedor;
import com.pagina.perfulandia.Proveedor.repository.ProveedorRepository;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> getProveedores(){
        return proveedorRepository.findAll();
    }

    public Proveedor saveProveedor(Proveedor proveedor){
        if (proveedor.getId() != 0) {
        throw new IllegalArgumentException("No se debe enviar un ID al crear un nuevo proveedor");
    }
        return proveedorRepository.save(proveedor);
    }

    public Proveedor getProveedorId(int id){
        return proveedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }

    public Proveedor updateProveedor(Proveedor proveedor){
        Optional<Proveedor> existente = proveedorRepository.findById(proveedor.getId());
        if (!existente.isPresent()) {
        throw new RuntimeException("Proveedor no encontrado");
    }
        return proveedorRepository.save(proveedor);
    }

    public String deleteProveedor(int id){
        Optional<Proveedor> existente = proveedorRepository.findById(id);
        if (!existente.isPresent()) {
        throw new RuntimeException("Proveedor con ID " + id + " no encontrado");
    }
        proveedorRepository.deleteById(id);
        return "Proveedor eliminado";
    }

}
