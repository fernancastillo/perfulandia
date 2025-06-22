package com.pagina.perfulandia.Proveedor.service;

import java.util.List;

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
        return proveedorRepository.save(proveedor);
    }

    public Proveedor getProveedorId(int id){
        return proveedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }

    public Proveedor updatProveedor(Proveedor proveedor){
        return proveedorRepository.save(proveedor);
    }

    public String deleteProveedor(int id){
        proveedorRepository.deleteById(id);
        return "Proveedor eliminado";
    }

}
