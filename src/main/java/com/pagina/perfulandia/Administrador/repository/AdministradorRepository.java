package com.pagina.perfulandia.Administrador.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pagina.perfulandia.Administrador.model.Administrador;


@Repository
public class AdministradorRepository {
    private List<Administrador> listaAdministradores = new ArrayList<>();

    public List<Administrador> obtenerAdministradores(){
        return listaAdministradores;
    }

    public Administrador buscarPorId(int id){
       for (Administrador a : listaAdministradores) {
            if (a.getId() == id){
                return a;
            }
       } 
       return null;
    }

    public Administrador guardarAdministrador(Administrador a){
        listaAdministradores.add(a);
        return a;
    }

    public Administrador actualizarAdministrador(Administrador a){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaAdministradores.size(); i++) {
            if (listaAdministradores.get(i).getId() == a.getId()) {
                id = a.getId();
                idPosicion = i;
            }
        }
        Administrador administrador1 = new Administrador();
        administrador1.setId(id);
        administrador1.setNombre(a.getNombre());
        administrador1.setAp_paterno(a.getAp_paterno());
        administrador1.setAp_materno(a.getAp_materno());
        administrador1.setCorreo(a.getCorreo());
        administrador1.setContrasenha(a.getContrasenha());
        administrador1.setNum_telefono(a.getNum_telefono());
        administrador1.setSueldo(a.getSueldo());
        administrador1.setId_sucursal(a.getId_sucursal());

        listaAdministradores.set(idPosicion, administrador1);
        return administrador1;
    }

    public void eliminarAdministrador(int id){
        Administrador administrador = buscarPorId(id);
        if (administrador != null) {
            listaAdministradores.remove(administrador);
        }
    }

}