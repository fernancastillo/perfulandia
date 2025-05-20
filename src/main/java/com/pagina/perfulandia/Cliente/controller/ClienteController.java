package com.pagina.perfulandia.Cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Cliente.model.Cliente;
import com.pagina.perfulandia.Cliente.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.getClientes();
    }

    @PostMapping
    public Cliente agregarCliente(@RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);    
    }

    @GetMapping("{id}")
    public Cliente buscarCliente(@PathVariable int id){
        return clienteService.getClienteId(id);
    }
    
    @PutMapping("{id}")
    public Cliente actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente){
        return clienteService.updateCliente(cliente);
    }
    
    @DeleteMapping("{id}")
    public String eliminarCliente(@PathVariable int id){
        return clienteService.deleteCliente(id);
    }


}
