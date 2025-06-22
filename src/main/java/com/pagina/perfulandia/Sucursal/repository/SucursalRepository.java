package com.pagina.perfulandia.Sucursal.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.pagina.perfulandia.Sucursal.model.Sucursal;


@Repository
public class SucursalRepository {
    private List<Sucursal> listaSucursal = new ArrayList<>();

    public List<Sucursal> obtenerSucursales(){
        return listaSucursal;
    }
    
    public Sucursal buscarPorId(int id){
        for (Sucursal s : listaSucursal) {
            if (s.getId()==id) {
                return s;
            } 
        }
        return null;
    }

    public Sucursal guardarSucursal(Sucursal s){
        listaSucursal.add(s);
        return s;
    }

    public Sucursal actualizarSucursal(Sucursal s){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaSucursal.size(); i++) {
            if (listaSucursal.get(i).getId() == s.getId()) {
                id = s.getId();
                idPosicion = i;
            }
        }
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setId(id);
        sucursal1.setNombre(s.getNombre());
        sucursal1.setDireccion(s.getDireccion());

        listaSucursal.set(idPosicion,sucursal1);
        return sucursal1;
    }

    public void eliminarSucursal(int id){
        Sucursal sucursal = buscarPorId(id);
        if (sucursal  !=null) {
            listaSucursal.remove(sucursal);
            
        }

    }





}
