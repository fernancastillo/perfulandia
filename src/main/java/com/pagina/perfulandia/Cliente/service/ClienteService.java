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

    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente getClienteId(int id){
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    public Cliente updateCliente (Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public String deleteCliente(int id){
        clienteRepository.deleteById(id);
        return "Cliente eliminado";
    }
}
