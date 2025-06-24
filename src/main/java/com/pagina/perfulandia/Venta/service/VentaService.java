package com.pagina.perfulandia.Venta.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.perfulandia.Venta.model.Venta;
import com.pagina.perfulandia.Venta.repository.VentaRepository;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> getVentas(){
        return ventaRepository.findAll();
    }

    public Venta saveVenta(Venta venta){
        if (venta.getId() != 0) {
        throw new IllegalArgumentException("No se debe enviar un ID al crear una nueva venta");
    }
        return ventaRepository.save(venta);
    }

    public Venta getVentaId(int id){
        return ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    public Venta updateVenta (Venta venta){
        Optional<Venta> existente = ventaRepository.findById(venta.getId());
        if (!existente.isPresent()) {
        throw new RuntimeException("Venta no encontrada");
    }
        return ventaRepository.save(venta);
    }

    public String deleteVenta(int id){
        Optional<Venta> existente = ventaRepository.findById(id);
        if (!existente.isPresent()) {
        throw new RuntimeException("Venta con ID " + id + " no encontrada");
    }
        ventaRepository.deleteById(id);
        return "Venta eliminada";
    }
    
}