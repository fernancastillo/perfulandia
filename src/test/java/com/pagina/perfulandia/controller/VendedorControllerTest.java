package com.pagina.perfulandia.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.pagina.perfulandia.Vendedor.controller.VendedorController;
import com.pagina.perfulandia.Vendedor.model.Vendedor;
import com.pagina.perfulandia.Vendedor.service.VendedorService;

@WebMvcTest(VendedorController.class)
public class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendedorService vendedorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Vendedor vendedor;

    @BeforeEach
    void setUp(){
        vendedor = new Vendedor();
        
        vendedor.setId(1);
        vendedor.setNombre("Benjamin");
        vendedor.setAp_paterno("Arellano");
        vendedor.setAp_materno("Gajardo");
        vendedor.setCorreo("benja.are@perfulandia.cl");
        vendedor.setContrasenha("gallardo3090");
        vendedor.setNum_telefono(954638283);
        vendedor.setSueldo(550000);
        vendedor.setId_sucursal(1);
    }

    @Test
    public void testGetAllVendedores() throws Exception{
        when(vendedorService.getVendedores()).thenReturn(List.of(vendedor));

        mockMvc.perform(get("/api/v1/vendedores"))//la url de nuestra api
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Benjamin"))
            .andExpect(jsonPath("$[0].ap_paterno").value("Arellano"))
            .andExpect(jsonPath("$[0].ap_materno").value("Gajardo"))
            .andExpect(jsonPath("$[0].correo").value("benja.are@perfulandia.cl"))
            .andExpect(jsonPath("$[0].contrasenha").value("gallardo3090"))
            .andExpect(jsonPath("$[0].num_telefono").value(954638283))
            .andExpect(jsonPath("$[0].sueldo").value(550000))
            .andExpect(jsonPath("$[0].id_sucursal").value(1));
    }
}
