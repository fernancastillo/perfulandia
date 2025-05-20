package com.pagina.perfulandia.Pedido.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagina.perfulandia.Pedido.model.Pedido;
import com.pagina.perfulandia.Pedido.service.PedidoService;



@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoService.getPedido();
    }

    @PostMapping
    public Pedido agregarPedido(@RequestBody Pedido pedido){
        return pedidoService.savePedido(pedido);    
    }

    @GetMapping("{id}")
    public Pedido buscaPedido(@PathVariable int id){
        return pedidoService.getPedidoId(id);
    }
    
    @PutMapping("{id}")
    public Pedido actualizaPedido(@PathVariable int id, @RequestBody Pedido pedido){
        return pedidoService.updatePedido(pedido);
    }
    
    @DeleteMapping("{id}")
    public String eliminarPedido(@PathVariable int id){
        return pedidoService.deletePedido(id);
    }


}
