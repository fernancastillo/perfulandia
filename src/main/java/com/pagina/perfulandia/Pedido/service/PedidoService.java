package com.pagina.perfulandia.Pedido.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Pedido.model.Pedido;
import com.pagina.perfulandia.Pedido.repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> gePedido(){
        return pedidoRepository.obtenerPedidos();
    }

    public Pedido savePedido(Pedido pedido){
        return pedidoRepository.guardarPedido(pedido);
    }

    public Pedido gePedidoId(int id){
        return pedidoRepository.buscarPorId(id);
    }
    
    public Pedido updatePedido (Pedido pedido){
        return pedidoRepository.actualizarPedido(pedido);
    }
    
    public String deletePedido(int id){
        pedidoRepository.eliminarPedido(id);
        return "Pedido eliminado";
    }
}
