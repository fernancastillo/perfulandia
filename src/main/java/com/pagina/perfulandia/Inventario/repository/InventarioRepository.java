package com.pagina.perfulandia.Inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pagina.perfulandia.Inventario.model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer>{


}
