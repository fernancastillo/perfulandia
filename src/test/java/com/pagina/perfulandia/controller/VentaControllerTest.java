package com.pagina.perfulandia.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.pagina.perfulandia.Venta.controller.VentaController;
import com.pagina.perfulandia.Venta.model.Venta;
import com.pagina.perfulandia.Venta.service.VentaService;

@WebMvcTest(VentaController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaService ventaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Venta venta;

    @BeforeEach
    void setUp(){

        venta = new Venta();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        venta.setId(1);

        try{
            Date fecha = sdf.parse("2025-05-19");
            venta.setFecha(fecha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        venta.setId_emp(1);
        venta.setId_cli(1);
        venta.setTotal(35000);
        venta.setDescuento(0);
    }

    @Test
    public void testGetAllVenta() throws Exception{
        when(ventaService.getVentas()).thenReturn(List.of(venta));

        mockMvc.perform(get("/api/v1/ventas"))//la url de nuestra api
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].fecha").value("2025-05-19T04:00:00.000+00:00"))
            .andExpect(jsonPath("$[0].id_emp").value(1))
            .andExpect(jsonPath("$[0].id_cli").value(1))
            .andExpect(jsonPath("$[0].total").value(35000))
            .andExpect(jsonPath("$[0].descuento").value(0));
    }
}
