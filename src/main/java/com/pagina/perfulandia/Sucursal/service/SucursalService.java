package com.pagina.perfulandia.Sucursal.service;
import java.util.List;
import java.util.Optional;

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
        if (sucursal.getId() != 0) {
        throw new IllegalArgumentException("No se debe enviar un ID al crear una nueva sucursal");
    }
        return sucursalRepository.save(sucursal);
    }

    public Sucursal getSucursalId(int id){
        return sucursalRepository.findById(id).orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + id));
    }

    public Sucursal updateSucursal(Sucursal sucursal){
        Optional<Sucursal> existente = sucursalRepository.findById(sucursal.getId());
        if (!existente.isPresent()) {
        throw new RuntimeException("Sucursal no encontrada");
    }
        return sucursalRepository.save(sucursal);
    }

    public String deleteSucursal(int id){
        Optional<Sucursal> existente = sucursalRepository.findById(id);
        if (!existente.isPresent()) {
        throw new RuntimeException("Sucursal con ID " + id + " no encontrado");
    }
        sucursalRepository.deleteById(id);
        return "Sucursal Eliminada";
    }


    


}
