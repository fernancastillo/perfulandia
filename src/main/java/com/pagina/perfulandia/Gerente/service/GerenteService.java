package com.pagina.perfulandia.Gerente.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Gerente.model.Gerente;
import com.pagina.perfulandia.Gerente.repository.GerenteRepository;


@Service
public class GerenteService {
    @Autowired
    private GerenteRepository gerenteRepository;

    public List<Gerente> getGerentes(){
        return gerenteRepository.findAll();
    }

    public Gerente saveGerente (Gerente gerente){
        if (gerente.getId() != 0) {
        throw new IllegalArgumentException("No se debe enviar un ID al crear un nuevo gerente");
    }
        return gerenteRepository.save(gerente);
    }

    public Gerente getGerenteId(int id){
        return gerenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Gerente no encontrado con ID: " + id));
    }

    public Gerente updateGerente (Gerente gerente){
        Optional<Gerente> existente = gerenteRepository.findById(gerente.getId());
        if (!existente.isPresent()) {
        throw new RuntimeException("Gerente no encontrado");
    }
        return gerenteRepository.save(gerente);
    }

    public String deleteGerente(int id){
        Optional<Gerente> existente = gerenteRepository.findById(id);
        if (!existente.isPresent()) {
        throw new RuntimeException("Gerente con ID " + id + " no encontrado");
    }
        gerenteRepository.deleteById(id);
        return "Gerente eliminado";
    }
}