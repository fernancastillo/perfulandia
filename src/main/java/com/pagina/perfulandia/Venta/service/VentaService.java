package com.pagina.perfulandia.Venta.service;
import java.util.List;

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
        return ventaRepository.save(venta);
    }

    public Venta getVentaId(int id){
        return ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    public Venta updateVenta (Venta venta){
        return ventaRepository.save(venta);
    }

    public String deleteVenta(int id){
        ventaRepository.deleteById(id);
        return "Venta eliminada";
    }
    
}