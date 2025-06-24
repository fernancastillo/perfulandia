
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

    @Test
    public void testGetSucursalById() throws Exception {

        int id = 1;
        when(sucursalService.getSucursalId(id)).thenReturn(sucursal);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/sucursales/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Perfulandia Meiggs"))
            .andExpect(jsonPath("$.direccion").value("Barrio Meiggs 4219")); 
    }

    @Test
    public void testCreateSucursales() throws Exception {

        when(sucursalService.saveSucursal(sucursal)).thenReturn(sucursal);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/sucursales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sucursal)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Perfulandia Meiggs"))
                .andExpect(jsonPath("$.direccion").value("Barrio Meiggs 4219")); 
    }

    @Test
    public void testUpdateSucursal() throws Exception {

        int id = 1;
        sucursal.setId(id);
        when(sucursalService.updateSucursal(sucursal)).thenReturn(sucursal);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/v1/sucursales/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sucursal)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.nombre").value("Perfulandia Meiggs"))
                .andExpect(jsonPath("$.direccion").value("Barrio Meiggs 4219")); 
    }

    @Test
    public void testDeleteSucursal() throws Exception {
        int id = 1;

        when(sucursalService.deleteSucursal(id)).thenReturn("Sucursal eliminada con éxito");
        
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/v1/sucursales/" + id))
            .andExpect(status().isOk())
            .andExpect(content().string("Sucursal eliminada con éxito"));

        verify(sucursalService, times(1)).deleteSucursal(id);
    }
}
