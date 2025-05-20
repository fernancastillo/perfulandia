package com.pagina.perfulandia.Envio.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Envio.model.Envio;
import com.pagina.perfulandia.Envio.repository.EnvioRepository;

@Service
public class EnvioService {
    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> getEnvios(){
        return envioRepository.obtenerEnvios();
    }

    public Envio saveEnvio(Envio envio){
        return envioRepository.guardarEnvio(envio);
    }

    public Envio getEnvioId(int id){
        return envioRepository.buscarPorId(id);
    }

    public Envio updateEnvio (Envio envio){
        return envioRepository.actualizarEnvio(envio);
    }

    public String deleteEnvio(int id){
        envioRepository.eliminarEnvio(id);
        return "Envio eliminado";
    }
}
