package com.pagina.perfulandia.Pedido.service;
import java.util.List;
import java.util.Optional;

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
        if (pedido.getId() != 0) {
        throw new IllegalArgumentException("No se debe enviar un ID al crear un nuevo pedido");
    }
        return pedidoRepository.save(pedido);
    }

    public Pedido getPedidoId(int id){
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }
    
    public Pedido updatePedido (Pedido pedido){
        Optional<Pedido> existente = pedidoRepository.findById(pedido.getId());
        if (!existente.isPresent()) {
        throw new RuntimeException("Pedido no encontrado");
    }
        return pedidoRepository.save(pedido);
    }
    
    public String deletePedido(int id){
        Optional<Pedido> existente = pedidoRepository.findById(id);
        if (!existente.isPresent()) {
        throw new RuntimeException("Pedido con ID " + id + " no encontrado");
    }
        pedidoRepository.deleteById(id);
        return "Pedido eliminado";
    }
}
