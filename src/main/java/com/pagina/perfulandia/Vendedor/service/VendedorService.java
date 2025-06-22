package com.pagina.perfulandia.Vendedor.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Vendedor.model.Vendedor;
import com.pagina.perfulandia.Vendedor.repository.VendedorRepository;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> getVendedores(){
        return vendedorRepository.findAll();
    }

    public Vendedor saveVendedor(Vendedor vendedor){
        return vendedorRepository.save(vendedor);
    }

    public Vendedor getVendedorId(int id){
        return vendedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + id));
    }

    public Vendedor updateVendedor (Vendedor vendedor){
        return vendedorRepository.save(vendedor);
    }

    public String deleteVendedor(int id){
        vendedorRepository.deleteById(id);
        return "Vendedor eliminado";
    }
}
