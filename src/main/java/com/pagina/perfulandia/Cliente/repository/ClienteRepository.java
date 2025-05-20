package com.pagina.perfulandia.Cliente.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.pagina.perfulandia.Cliente.model.Cliente;


@Repository
public class ClienteRepository {
    private List<Cliente> listaClientes = new ArrayList<>();

    public List<Cliente> obtenerClientes(){
        return listaClientes;
    }

    public Cliente buscarPorId(int id){
       for (Cliente c : listaClientes) {
            if (c.getId() == id){
                return c;
            }
       } 
       return null;
    }

    public Cliente guardarCliente(Cliente c){
        listaClientes.add(c);
        return c;
    }

    public Cliente actualizarCliente(Cliente c){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId() == c.getId()) {
                id = c.getId();
                idPosicion = i;
            }
        }
        Cliente cliente1 = new Cliente();
        cliente1.setId(id);
        cliente1.setNombre(c.getNombre());
        cliente1.setAp_paterno(c.getAp_paterno());
        cliente1.setAp_materno(c.getAp_materno());
        cliente1.setCorreo(c.getCorreo());
        cliente1.setContraseña(c.getContraseña());
        cliente1.setNum_telefono(c.getNum_telefono());

        listaClientes.set(idPosicion, cliente1);
        return cliente1;
    }

    public void eliminarCliente(int id){
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            listaClientes.remove(cliente);
        }
    }

}
