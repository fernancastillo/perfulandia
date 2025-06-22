
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

import com.pagina.perfulandia.Sucursal.controller.SucursalController;
import com.pagina.perfulandia.Sucursal.model.Sucursal;
import com.pagina.perfulandia.Sucursal.service.SucursalService;

@WebMvcTest(SucursalController.class)
public class SucursalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SucursalService sucursalService;

    @Autowired
    private ObjectMapper objectMapper;

    private Sucursal sucursal;

    @BeforeEach
    void setUp(){
        sucursal = new Sucursal();
        
        sucursal.setId(1);
        sucursal.setNombre("Perfulandia Meiggs");
        sucursal.setDireccion("Barrio Meiggs 4219");
    }

    @Test
    public void testGetAllSucursales() throws Exception{
        when(sucursalService.getSucursales()).thenReturn(List.of(sucursal));

        mockMvc.perform(get("/api/v1/sucursales"))//la url de nuestra api
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Perfulandia Meiggs"))
            .andExpect(jsonPath("$[0].direccion").value("Barrio Meiggs 4219"));            
    }
}
