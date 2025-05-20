package com.pagina.perfulandia.Envio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Envio.model.Envio;
import com.pagina.perfulandia.Envio.service.EnvioService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {
    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> listarEnvios(){
        return envioService.getEnvios();
    }

    @PostMapping
    public Envio agregarEnvio(@RequestBody Envio envio){
        return envioService.saveEnvio(envio);    
    }

    @GetMapping("{id}")
    public Envio buscarEnvio(@PathVariable int id){
        return envioService.getEnvioId(id);
    }
    
    @PutMapping("{id}")
    public Envio actualizarEnvio(@PathVariable int id, @RequestBody Envio envio){
        return envioService.updateEnvio(envio);
    }
    
    @DeleteMapping("{id}")
    public String eliminarEnvio(@PathVariable int id){
        return envioService.deleteEnvio(id);
    }


}