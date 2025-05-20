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
        return gerenteRepository.obtenerGerentes();
    }

    public Gerente saveGerente (Gerente gerente){
        return gerenteRepository.guardarGerente(gerente);
    }

    public Gerente getGerenteId(int id){
        return gerenteRepository.buscarPorId(id);
    }

    public Gerente updateGerente (Gerente gerente){
        return gerenteRepository.actualizarGerente(gerente);
    }

    public String deleteGerente(int id){
        gerenteRepository.eliminarGerente(id);
        return "Gerente eliminado";
    }
}