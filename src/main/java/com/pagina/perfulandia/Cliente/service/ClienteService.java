package com.pagina.perfulandia.Cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Cliente.model.Cliente;
import com.pagina.perfulandia.Cliente.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getProductos(){
        return clienteRepository.obtenerClientes();
    }

    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.guardarCliente(cliente);
    }

    public Cliente getClienteId(int id){
        return clienteRepository.buscarPorId(id);
    }

    public Cliente updateProducto (Cliente producto){
        return clienteRepository.actualizarCliente(producto);
    }

    public String deleteProducto(int id){
        clienteRepository.eliminarCliente(id);
        return "Cliente eliminado";
    }
}
