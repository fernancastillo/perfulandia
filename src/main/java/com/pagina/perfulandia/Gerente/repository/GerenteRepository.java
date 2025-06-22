package com.pagina.perfulandia.Gerente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagina.perfulandia.Gerente.model.Gerente;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Integer> {


}