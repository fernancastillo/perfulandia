package com.pagina.perfulandia.Sucursal.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Sucursal.model.Sucursal;
import com.pagina.perfulandia.Sucursal.repository.SucursalRepository;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> getSucursales(){
        return sucursalRepository.obtenerSucursales();
    }

    public Sucursal saveSucursal(Sucursal sucursal){
        return sucursalRepository.guardarSucursal(sucursal);
    }

    public Sucursal getSucursalId(int id){
        return sucursalRepository.buscarPorId(id);
    }

    public Sucursal updateSucursal(Sucursal sucursal){
        return sucursalRepository.actualizarSucursal(sucursal);
    }

    public String deleteSucursal(int id){
        sucursalRepository.eliminarSucursal(id);
        return "Sucursal Eliminada";
    }


    


}
