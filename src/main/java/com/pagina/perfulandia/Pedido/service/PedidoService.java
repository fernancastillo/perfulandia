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

    public List<Pedido> getPedidos(){
        return pedidoRepository.findAll();
    }

    public Pedido savePedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Pedido getPedidoId(int id){
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }
    
    public Pedido updatePedido (Pedido pedido){
        return pedidoRepository.save(pedido);
    }
    
    public String deletePedido(int id){
        pedidoRepository.deleteById(id);
        return "Pedido eliminado";
    }
}
