package com.pagina.perfulandia.Proveedor.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pagina.perfulandia.Proveedor.model.Proveedor;

@Repository
public class ProveedorRepository {
    private List<Proveedor> listaProveedores = new ArrayList<>();

    public List<Proveedor> obtenerProveedores(){
        return listaProveedores;
    }

    public Proveedor buscarPorId(int id){
        for (Proveedor p : listaProveedores) {
            if (p.getId() == id) {
                return p;
                
            }
            
        }
        return null;
    }

    public Proveedor guardarProveedor(Proveedor p){
        listaProveedores.add(p);
        return p;
    }

    public Proveedor actualizarProveedor(Proveedor p){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaProveedores.size(); i++) {
            if (listaProveedores.get(i).getId() == p.getId()) {
                id = p.getId();
                idPosicion = i;
            }
        }
        Proveedor proveedor1 = new Proveedor();
        proveedor1.setId(id);
        proveedor1.setNombre(p.getNombre());
        proveedor1.setTelefono(p.getTelefono());
        proveedor1.setDireccion(p.getDireccion());

        listaProveedores.set(idPosicion, proveedor1);
        return proveedor1;
    }

    public void eliminarProveedor(int id){
        Proveedor proveedor = buscarPorId(id);
        if (proveedor != null) {
            listaProveedores.remove(proveedor);           
        }
    }
    

}
