package com.pagina.perfulandia.Administrador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Administrador.model.Administrador;
import com.pagina.perfulandia.Administrador.repository.AdministradorRepository;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> getAdministradores(){
        return administradorRepository.findAll();
    }

    public Administrador saveAdministrador(Administrador administrador){
        return administradorRepository.save(administrador);
    }

    public Administrador getAdministradorId(int id){
        return administradorRepository.findById(id).orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));
    }

    public Administrador updateAdministrador (Administrador administrador){
        return administradorRepository.save(administrador);
    }

    public String deleteAdministrador(int id){
        administradorRepository.deleteById(id);
        return "Administrador eliminado";
    }
}