package com.pagina.perfulandia.Sucursal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagina.perfulandia.Sucursal.model.Sucursal;


@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {


}
