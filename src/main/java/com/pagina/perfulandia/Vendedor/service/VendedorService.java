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
        return vendedorRepository.obtenerVendedor();
    }

    public Vendedor saveVendedor(Vendedor vendedor){
        return vendedorRepository.guardarVendedor(vendedor);
    }

    public Vendedor getVendedorId(int id){
        return vendedorRepository.buscarPorId(id);
    }

    public Vendedor updateVendedor (Vendedor vendedor){
        return vendedorRepository.actualizarVendedor(vendedor);
    }

    public String deleteVendedor(int id){
        vendedorRepository.eliminarVendedor(id);
        return "Vendedor eliminado";
    }
}
