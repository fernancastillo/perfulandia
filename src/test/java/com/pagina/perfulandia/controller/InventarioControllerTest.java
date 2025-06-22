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

import com.pagina.perfulandia.Inventario.controller.InventarioController;
import com.pagina.perfulandia.Inventario.model.Inventario;
import com.pagina.perfulandia.Inventario.service.InventarioService;

@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Inventario inventario;

    @BeforeEach
    void setUp(){
        inventario = new Inventario();
        
        inventario.setId(1);
        inventario.setId_producto(1);
        inventario.setId_sucursal(1);
        inventario.setCantidad(172);
    }

    @Test
    public void testGetAllInventarios() throws Exception{
        when(inventarioService.getInventarios()).thenReturn(List.of(inventario));

        mockMvc.perform(get("/api/v1/inventarios"))//la url de nuestra api
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].id_producto").value(1))
            .andExpect(jsonPath("$[0].id_sucursal").value(1))
            .andExpect(jsonPath("$[0].cantidad").value(172));
    }
}
