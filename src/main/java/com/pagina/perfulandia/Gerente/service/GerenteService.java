package com.pagina.perfulandia.Gerente.service;


import java.util.List;

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
        return gerenteRepository.save(gerente);
    }

    public Gerente getGerenteId(int id){
        return gerenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Gerente no encontrado con ID: " + id));
    }

    public Gerente updateGerente (Gerente gerente){
        return gerenteRepository.save(gerente);
    }

    public String deleteGerente(int id){
        gerenteRepository.deleteById(id);
        return "Gerente eliminado";
    }
}