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
import org.springframework.http.MediaType;
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

    @Test
    public void testGetVendedorById() throws Exception {

        int id = 1;
        when(vendedorService.getVendedorId(id)).thenReturn(vendedor);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/vendedores/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Benjamin"))
            .andExpect(jsonPath("$.ap_paterno").value("Arellano"))
            .andExpect(jsonPath("$.ap_materno").value("Gajardo"))
            .andExpect(jsonPath("$.correo").value("benja.are@perfulandia.cl"))
            .andExpect(jsonPath("$.contrasenha").value("gallardo3090"))
            .andExpect(jsonPath("$.num_telefono").value(954638283))
            .andExpect(jsonPath("$.sueldo").value(550000))
            .andExpect(jsonPath("$.id_sucursal").value(1));
    }

    @Test
    public void testCreateVendedor() throws Exception {

        when(vendedorService.saveVendedor(vendedor)).thenReturn(vendedor);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/vendedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendedor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Benjamin"))
                .andExpect(jsonPath("$.ap_paterno").value("Arellano"))
                .andExpect(jsonPath("$.ap_materno").value("Gajardo"))
                .andExpect(jsonPath("$.correo").value("benja.are@perfulandia.cl"))
                .andExpect(jsonPath("$.contrasenha").value("gallardo3090"))
                .andExpect(jsonPath("$.num_telefono").value(954638283))
                .andExpect(jsonPath("$.sueldo").value(550000))
                .andExpect(jsonPath("$.id_sucursal").value(1));
    }

    @Test
    public void testUpdateVendedor() throws Exception {

        int id = 1;
        vendedor.setId(id);
        when(vendedorService.updateVendedor(vendedor)).thenReturn(vendedor);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/v1/vendedores/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendedor)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.nombre").value("Benjamin"))
                .andExpect(jsonPath("$.ap_paterno").value("Arellano"))
                .andExpect(jsonPath("$.ap_materno").value("Gajardo"))
                .andExpect(jsonPath("$.correo").value("benja.are@perfulandia.cl"))
                .andExpect(jsonPath("$.contrasenha").value("gallardo3090"))
                .andExpect(jsonPath("$.num_telefono").value(954638283))
                .andExpect(jsonPath("$.sueldo").value(550000))
                .andExpect(jsonPath("$.id_sucursal").value(1));
    }

    @Test
    public void testDeleteVendedor() throws Exception {
        int id = 1;

        when(vendedorService.deleteVendedor(id)).thenReturn("Vendedor eliminado con éxito");
        
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/v1/vendedores/" + id))
            .andExpect(status().isOk())
            .andExpect(content().string("Vendedor eliminado con éxito"));

        verify(vendedorService, times(1)).deleteVendedor(id);
    }
}
