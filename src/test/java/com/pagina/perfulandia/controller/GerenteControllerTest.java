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

}
