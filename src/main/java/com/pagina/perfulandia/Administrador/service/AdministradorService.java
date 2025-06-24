package com.pagina.perfulandia.Administrador.service;

import java.util.List;
import java.util.Optional;

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
        if (administrador.getId() != 0) {
        throw new IllegalArgumentException("No se debe enviar un ID al crear un nuevo administrador");
    }
        return administradorRepository.save(administrador);
    }

    public Administrador getAdministradorId(int id){
        return administradorRepository.findById(id).orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));
    }

    public Administrador updateAdministrador (Administrador administrador){
        Optional<Administrador> existente = administradorRepository.findById(administrador.getId());
        if (!existente.isPresent()) {
        throw new RuntimeException("Administrador no encontrado");
    }
        return administradorRepository.save(administrador);
    }

    public String deleteAdministrador(int id){
        Optional<Administrador> existente = administradorRepository.findById(id);
        if (!existente.isPresent()) {
        throw new RuntimeException("Administrador con ID " + id + " no encontrado");
    }
        administradorRepository.deleteById(id);
        return "Administrador eliminado";
    }
}