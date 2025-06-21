package com.pagina.perfulandia.Pedido.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pagina.perfulandia.Pedido.model.Pedido;

@Repository
public class PedidoRepository {
    private List<Pedido> listaPedidos = new ArrayList<>();

    public List<Pedido> obtenerPedidos(){
        return listaPedidos;
    }

    public Pedido buscarPorId(int id){
       for (Pedido p : listaPedidos) {
            if (p.getId() == id){
                return p;
            }
       } 
       return null;
    }

    public Pedido guardarPedido(Pedido p){
        listaPedidos.add(p);
        return p;
    }

    public Pedido actualizarPedido(Pedido p){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaPedidos.size(); i++) {
            if (listaPedidos.get(i).getId() == p.getId()) {
                id = p.getId();
                idPosicion = i;
            }
        }
        Pedido pedido1 = new Pedido();
        pedido1.setId(id);
        pedido1.setFecha(p.getFecha());
        pedido1.setEstado(p.getEstado());
        pedido1.setTipo_pedido(p.getTipo_pedido());
        pedido1.setId_receptor(p.getId_receptor());

        listaPedidos.set(idPosicion, pedido1);
        return pedido1;
    }

    public void eliminarPedido(int id){
        Pedido pedido = buscarPorId(id);
        if (pedido != null) {
            listaPedidos.remove(pedido);
        }
    }

}
