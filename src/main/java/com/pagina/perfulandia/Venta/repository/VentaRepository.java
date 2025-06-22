package com.pagina.perfulandia.Venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pagina.perfulandia.Venta.model.Venta;



@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

}
