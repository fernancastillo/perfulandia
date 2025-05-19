package com.pagina.perfulandia.Venta.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.pagina.perfulandia.Venta.model.Venta;



@Repository
public class VentaRepository {

   private List<Venta> listaVentas = new ArrayList<>();

    public List<Venta> obtenerVentas(){
        return listaVentas;
    }

    public Venta buscarPorId(int id){
       for (Venta v : listaVentas) {
            if (v.getId() == id){
                return v;
            }
       } 
       return null;
    }

    public Venta guardarVenta(Venta v){
        listaVentas.add(v);
        return v;
    }

    public Venta actualizarVenta(Venta v){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaVentas.size(); i++) {
            if (listaVentas.get(i).getId() == v.getId()) {
                id = v.getId();
                idPosicion = i;
            }
        }
        Venta venta1 = new Venta();
        venta1.setId(id);
        venta1.setFecha(v.getFecha());
        venta1.setId_emp(v.getId_emp());
        venta1.setId_cli(v.getId_cli());
        venta1.setTotal(v.getTotal());
        venta1.setDescuento(v.getDescuento());

        listaVentas.set(idPosicion, venta1);
        return venta1;
    }

    public void eliminarVenta(int id){
        Venta venta = buscarPorId(id);
        if (venta != null) {
            listaVentas.remove(venta);
        }
    }


}
