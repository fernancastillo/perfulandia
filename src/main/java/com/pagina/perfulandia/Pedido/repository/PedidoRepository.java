package com.pagina.perfulandia.Pedido.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pagina.perfulandia.Pedido.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
 

}
