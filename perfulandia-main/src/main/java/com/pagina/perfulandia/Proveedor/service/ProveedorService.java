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

    public List<Proveedor> getProveedor(){
        return proveedorRepository.obtenerProveedores();
    }

    public Proveedor saveProveedor(Proveedor proveedor){
        return proveedorRepository.guardarProveedor(proveedor);
    }

    public Proveedor getProveedorId(int id){
        return proveedorRepository.buscarPorId(id);
    }

    public Proveedor updatProveedor(Proveedor proveedor){
        return proveedorRepository.actualizarProveedor(proveedor);
    }

    public String deleteProveedor(int id){
        proveedorRepository.eliminarProveedor(id);
        return "Proveedor eliminado";
    }

}
