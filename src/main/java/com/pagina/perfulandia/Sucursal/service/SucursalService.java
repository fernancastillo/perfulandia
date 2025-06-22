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
        return sucursalRepository.findAll();
    }

    public Sucursal saveSucursal(Sucursal sucursal){
        return sucursalRepository.save(sucursal);
    }

    public Sucursal getSucursalId(int id){
        return sucursalRepository.findById(id).orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + id));
    }

    public Sucursal updateSucursal(Sucursal sucursal){
        return sucursalRepository.save(sucursal);
    }

    public String deleteSucursal(int id){
        sucursalRepository.deleteById(id);
        return "Sucursal Eliminada";
    }


    


}
