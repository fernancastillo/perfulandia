package com.pagina.perfulandia.Gerente.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pagina.perfulandia.Gerente.model.Gerente;

@Repository
public class GerenteRepository {
    private List<Gerente> listaGerentes = new ArrayList<>();

    public List<Gerente> obtenerGerentes(){
        return listaGerentes;
    }

    public Gerente buscarPorId(int id){
       for (Gerente g : listaGerentes) {
            if (g.getId() == id){
                return g;
            }
       } 
       return null;
    }

    public Gerente guardarGerente(Gerente g){
        listaGerentes.add(g);
        return g;
    }

    public Gerente actualizarGerente(Gerente g){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaGerentes.size(); i++) {
            if (listaGerentes.get(i).getId() == g.getId()) {
                id = g.getId();
                idPosicion = i;
            }
        }
        Gerente gerente1 = new Gerente();
        gerente1.setId(id);
        gerente1.setNombre(g.getNombre());
        gerente1.setAp_paterno(g.getAp_paterno());
        gerente1.setAp_materno(g.getAp_materno());
        gerente1.setCorreo(g.getCorreo());
        gerente1.setContrasenha(g.getContrasenha());
        gerente1.setNum_telefono(g.getNum_telefono());
        gerente1.setSueldo(g.getSueldo());
        gerente1.setId_sucursal(g.getId_sucursal());

        listaGerentes.set(idPosicion, gerente1);
        return gerente1;
    }

    public void eliminarGerente(int id){
        Gerente gerente = buscarPorId(id);
        if (gerente != null) {
            listaGerentes.remove(gerente);
        }
    }

}