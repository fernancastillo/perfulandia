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

import com.pagina.perfulandia.Gerente.controller.GerenteController;
import com.pagina.perfulandia.Gerente.model.Gerente;
import com.pagina.perfulandia.Gerente.service.GerenteService;

@WebMvcTest(GerenteController.class)
public class GerenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerenteService gerenteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Gerente gerente;

    @BeforeEach
    void setUp(){
        gerente = new Gerente();
        
        gerente.setId(1);
        gerente.setNombre("Jose");
        gerente.setAp_paterno("Penaloza");
        gerente.setAp_materno("Robinson");
        gerente.setCorreo("j_penalozar@perfulandia.cl");
        gerente.setContrasenha("ap/i s45lh*8d");
        gerente.setNum_telefono(954873299);
        gerente.setSueldo(10200000);
        gerente.setId_sucursal(1);
    }

    @Test
    public void testGetAllGerentes() throws Exception{
        when(gerenteService.getGerentes()).thenReturn(List.of(gerente));

        mockMvc.perform(get("/api/v1/gerentes"))//la url de nuestra api
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Jose"))
            .andExpect(jsonPath("$[0].ap_paterno").value("Penaloza"))
            .andExpect(jsonPath("$[0].ap_materno").value("Robinson"))
            .andExpect(jsonPath("$[0].correo").value("j_penalozar@perfulandia.cl"))
            .andExpect(jsonPath("$[0].contrasenha").value("ap/i s45lh*8d"))
            .andExpect(jsonPath("$[0].num_telefono").value(954873299))
            .andExpect(jsonPath("$[0].sueldo").value(10200000))
            .andExpect(jsonPath("$[0].id_sucursal").value(1));
    }

        @Test
    public void testGetGerenteById() throws Exception {

        int id = 1;
        when(gerenteService.getGerenteId(id)).thenReturn(gerente);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/gerentes/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Jose"))
            .andExpect(jsonPath("$.ap_paterno").value("Penaloza"))
            .andExpect(jsonPath("$.ap_materno").value("Robinson"))
            .andExpect(jsonPath("$.correo").value("j_penalozar@perfulandia.cl"))
            .andExpect(jsonPath("$.contrasenha").value("ap/i s45lh*8d"))
            .andExpect(jsonPath("$.num_telefono").value(954873299))
            .andExpect(jsonPath("$.sueldo").value(10200000))
            .andExpect(jsonPath("$.id_sucursal").value(1));
    }

    @Test
    public void testCreateGerente() throws Exception {

        when(gerenteService.saveGerente(gerente)).thenReturn(gerente);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/gerentes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gerente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Jose"))
                .andExpect(jsonPath("$.ap_paterno").value("Penaloza"))
                .andExpect(jsonPath("$.ap_materno").value("Robinson"))
                .andExpect(jsonPath("$.correo").value("j_penalozar@perfulandia.cl"))
                .andExpect(jsonPath("$.contrasenha").value("ap/i s45lh*8d"))
                .andExpect(jsonPath("$.num_telefono").value(954873299))
                .andExpect(jsonPath("$.sueldo").value(10200000))
                .andExpect(jsonPath("$.id_sucursal").value(1));
    }

    @Test
    public void testUpdateGerente() throws Exception {

        int id = 1;
        gerente.setId(id);
        when(gerenteService.updateGerente(gerente)).thenReturn(gerente);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/v1/gerentes/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gerente)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.nombre").value("Jose"))
                .andExpect(jsonPath("$.ap_paterno").value("Penaloza"))
                .andExpect(jsonPath("$.ap_materno").value("Robinson"))
                .andExpect(jsonPath("$.correo").value("j_penalozar@perfulandia.cl"))
                .andExpect(jsonPath("$.contrasenha").value("ap/i s45lh*8d"))
                .andExpect(jsonPath("$.num_telefono").value(954873299))
                .andExpect(jsonPath("$.sueldo").value(10200000))
                .andExpect(jsonPath("$.id_sucursal").value(1));
    }

    @Test
    public void testDeleteGerente() throws Exception {
        int id = 1;

        when(gerenteService.deleteGerente(id)).thenReturn("Gerente eliminado con éxito");
        
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/v1/gerentes/" + id))
            .andExpect(status().isOk())
            .andExpect(content().string("Gerente eliminado con éxito"));

        verify(gerenteService, times(1)).deleteGerente(id);
    }

}
