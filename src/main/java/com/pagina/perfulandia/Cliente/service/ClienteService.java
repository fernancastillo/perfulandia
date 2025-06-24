package com.pagina.perfulandia.Cliente.service;

import java.util.List;
import java.util.Optional;

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
        if (cliente.getId() != 0) {
        throw new IllegalArgumentException("No se debe enviar un ID al crear un nuevo cliente");
    }
        return clienteRepository.save(cliente);
    }

    public Cliente getClienteId(int id){
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    public Cliente updateCliente (Cliente cliente){
        Optional<Cliente> existente = clienteRepository.findById(cliente.getId());
        if (!existente.isPresent()) {
        throw new RuntimeException("Cliente no encontrado");
    }
        return clienteRepository.save(cliente);
    }

    public String deleteCliente(int id){
        Optional<Cliente> existente = clienteRepository.findById(id);
        if (!existente.isPresent()) {
        throw new RuntimeException("Cliente con ID " + id + " no encontrado");
    }
        clienteRepository.deleteById(id);
        return "Cliente eliminado";
    }
}
