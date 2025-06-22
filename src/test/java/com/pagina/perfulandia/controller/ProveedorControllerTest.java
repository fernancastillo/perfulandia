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


import com.pagina.perfulandia.Proveedor.controller.ProveedorController;
import com.pagina.perfulandia.Proveedor.model.Proveedor;
import com.pagina.perfulandia.Proveedor.service.ProveedorService;

@WebMvcTest(ProveedorController.class)
public class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProveedorService proveedorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Proveedor proveedor;

    @BeforeEach
    void setUp(){
        proveedor = new Proveedor();
        
        proveedor.setId(1);
        proveedor.setNombre("Zaafaran");
        proveedor.setTelefono(954658748);
        proveedor.setDireccion("Calle Millan 5486");
    }

    @Test
    public void testGetAllProveedores() throws Exception{
        when(proveedorService.getProveedores()).thenReturn(List.of(proveedor));

        mockMvc.perform(get("/api/v1/proveedores"))//la url de nuestra api
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Zaafaran"))
            .andExpect(jsonPath("$[0].telefono").value(954658748))
            .andExpect(jsonPath("$[0].direccion").value("Calle Millan 5486"));            
    }
}
