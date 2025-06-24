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
import org.springframework.http.MediaType;
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
        venta.setDescuento(Float.valueOf(0));
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
    
    @Test
    public void testVentaById() throws Exception {

        int id = 1;
        when(ventaService.getVentaId(id)).thenReturn(venta);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/ventas/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fecha").value("2025-05-19T04:00:00.000+00:00"))
            .andExpect(jsonPath("$.id_emp").value(1))
            .andExpect(jsonPath("$.id_cli").value(1))
            .andExpect(jsonPath("$.total").value(35000))
            .andExpect(jsonPath("$.descuento").value(0));
    }

    @Test
    public void testCreateVenta() throws Exception {

        when(ventaService.saveVenta(venta)).thenReturn(venta);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(venta)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fecha").value("2025-05-19T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.id_emp").value(1))
                .andExpect(jsonPath("$.id_cli").value(1))
                .andExpect(jsonPath("$.total").value(35000))
                .andExpect(jsonPath("$.descuento").value(0));
    }

    @Test
    public void testUpdateVenta() throws Exception {

        int id = 1;
        venta.setId(id);
        when(ventaService.updateVenta(venta)).thenReturn(venta);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/v1/ventas/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(venta)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.fecha").value("2025-05-19T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.id_emp").value(1))
                .andExpect(jsonPath("$.id_cli").value(1))
                .andExpect(jsonPath("$.total").value(35000))
                .andExpect(jsonPath("$.descuento").value(0));
    }

    @Test
    public void testDeleteVenta() throws Exception {
        int id = 1;

        when(ventaService.deleteVenta(id)).thenReturn("Venta eliminada con éxito");
        
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/v1/ventas/" + id))
            .andExpect(status().isOk())
            .andExpect(content().string("Venta eliminada con éxito"));

        verify(ventaService, times(1)).deleteVenta(id);
    }
}
