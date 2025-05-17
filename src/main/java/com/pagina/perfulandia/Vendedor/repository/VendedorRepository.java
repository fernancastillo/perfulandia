package com.pagina.perfulandia.Vendedor.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pagina.perfulandia.Vendedor.model.Vendedor;

@Repository
public class VendedorRepository {
    private List<Vendedor> listaVendedores = new ArrayList<>();

    public List<Vendedor> obtenerVendedor(){
        return listaVendedores;
    }

    public Vendedor buscarPorId(int id){
       for (Vendedor p : listaVendedores) {
            if (p.getId() == id){
                return p;
            }
       } 
       return null;
    }

    public Vendedor guardarVendedor(Vendedor p){
        listaVendedores.add(p);
        return p;
    }

    public Vendedor actualizarProducto(Vendedor p){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getId() == p.getId()) {
                id = p.getId();
                idPosicion = i;
            }
        }
        Vendedor vendedor1 = new Vendedor();
        vendedor1.setId(id);
        vendedor1.setNombre(p.getNombre());
        vendedor1.setAp_paterno(p.getAp_paterno());
        vendedor1.setAp_materno(p.getAp_materno());
        vendedor1.setCorreo(p.getCorreo());
        vendedor1.setContrasenha(p.getContrasenha());
        vendedor1.setNum_telefono(p.getNum_telefono());
        vendedor1.setSueldo(p.getSueldo());
        vendedor1.setId_sucursal(p.getId_sucursal());

        listaVendedores.set(idPosicion, vendedor1);
        return vendedor1;
    }

    public void eliminarVendedor(int id){
        Vendedor vendedor = buscarPorId(id);
        if (vendedor != null) {
            listaVendedores.remove(vendedor);
        }
    }

}
