package com.pagina.perfulandia.Cliente.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagina.perfulandia.Cliente.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
