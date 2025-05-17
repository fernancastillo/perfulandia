package com.pagina.perfulandia.Inventario.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.pagina.perfulandia.Inventario.model.Inventario;

@Repository
public class InventarioRepository {
    private List<Inventario> listaInventarios = new ArrayList<>();

    public List<Inventario> obtenerInventarios(){
        return listaInventarios;
    }

    public Inventario buscarPorId(int id){
        for (Inventario i : listaInventarios) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public Inventario guardarInventario(Inventario i){
        listaInventarios.add(i);
        return i;
    }

    public Inventario actualizarInventario(Inventario ii){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaInventarios.size(); i++) {
            if (listaInventarios.get(i).getId()==ii.getId()) {
                id = ii.getId();
                idPosicion = i;
            }
            
        }
        Inventario inventario1 = new Inventario();
        inventario1.setId(id);
        inventario1.setId_producto(ii.getId_producto());
        inventario1.setId_sucursal(ii.getId_sucursal());
        inventario1.setCantidad(ii.getCantidad());

        listaInventarios.set(idPosicion, inventario1);
        return inventario1;
    }

    public void eliminarInventario(int id){
        Inventario inventario = buscarPorId(id);
        if (inventario !=null) {
            listaInventarios.remove(inventario);
            
        }
    }
    


}
