package com.pagina.perfulandia.Envio.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pagina.perfulandia.Envio.model.Envio;

@Repository
public class EnvioRepository {
    private List<Envio> listaEnvios = new ArrayList<>();

    public List<Envio> obtenerEnvios(){
        return listaEnvios;
    }

    public Envio buscarPorId(int id){
       for (Envio p : listaEnvios) {
            if (p.getId() == id){
                return p;
            }
       } 
       return null;
    }

    public Envio guardarEnvio(Envio e){
        listaEnvios.add(e);
        return e;
    }

    public Envio actualizarEnvio(Envio e){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaEnvios.size(); i++) {
            if (listaEnvios.get(i).getId() == e.getId()) {
                id = e.getId();
                idPosicion = i;
            }
        }
        Envio envio1 = new Envio();
        envio1.setId(id);
        envio1.setId_pedido(e.getId_pedido());
        envio1.setFecha_envio(e.getFecha_envio());
        envio1.setEstado(e.getEstado());

        listaEnvios.set(idPosicion, envio1);
        return envio1;
    }

    public void eliminarEnvio(int id){
        Envio envio = buscarPorId(id);
        if (envio != null) {
            listaEnvios.remove(envio);
        }
    }

}
