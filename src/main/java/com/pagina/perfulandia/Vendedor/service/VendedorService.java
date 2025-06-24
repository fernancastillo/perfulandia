package com.pagina.perfulandia.Vendedor.service;
import java.util.List;
import java.util.Optional;

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
        if (vendedor.getId() != 0) {
        throw new IllegalArgumentException("No se debe enviar un ID al crear un nuevo vendedor");
    }
        return vendedorRepository.save(vendedor);
    }

    public Vendedor getVendedorId(int id){
        return vendedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + id));
    }

    public Vendedor updateVendedor (Vendedor vendedor){
        Optional<Vendedor> existente = vendedorRepository.findById(vendedor.getId());
        if (!existente.isPresent()) {
        throw new RuntimeException("Vendedor no encontrado");
    }
        return vendedorRepository.save(vendedor);
    }

    public String deleteVendedor(int id){
        Optional<Vendedor> existente = vendedorRepository.findById(id);
        if (!existente.isPresent()) {
        throw new RuntimeException("Vendedor con ID " + id + " no encontrado");
    }
        vendedorRepository.deleteById(id);
        return "Vendedor eliminado";
    }
}
