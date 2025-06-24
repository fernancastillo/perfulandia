package com.pagina.perfulandia.Administrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagina.perfulandia.Administrador.model.Administrador;


@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{

}