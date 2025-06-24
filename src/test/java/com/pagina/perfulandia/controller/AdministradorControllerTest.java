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

import com.pagina.perfulandia.Administrador.controller.AdministradorController;
import com.pagina.perfulandia.Administrador.model.Administrador;
import com.pagina.perfulandia.Administrador.service.AdministradorService;


@WebMvcTest(AdministradorController.class)
public class AdministradorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdministradorService administradorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Administrador administrador;

    @BeforeEach
    void setUp(){
        administrador = new Administrador();
        
        administrador.setId(1);
        administrador.setNombre("Luis");
        administrador.setAp_paterno("Burgos");
        administrador.setAp_materno("Lavin");
        administrador.setCorreo("l_burgosl@perfulandia.cl");
        administrador.setContrasenha("s8d45ap/ilh*/");
        administrador.setNum_telefono(954123658);
        administrador.setSueldo(1200000);
        administrador.setId_sucursal(1);
    }

    @Test
    public void testGetAllAdministradores() throws Exception{
        when(administradorService.getAdministradores()).thenReturn(List.of(administrador));

        mockMvc.perform(get("/api/v1/administradores"))//la url de nuestra api
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Luis"))
            .andExpect(jsonPath("$[0].ap_paterno").value("Burgos"))
            .andExpect(jsonPath("$[0].ap_materno").value("Lavin"))
            .andExpect(jsonPath("$[0].correo").value("l_burgosl@perfulandia.cl"))
            .andExpect(jsonPath("$[0].contrasenha").value("s8d45ap/ilh*/"))
            .andExpect(jsonPath("$[0].num_telefono").value(954123658))
            .andExpect(jsonPath("$[0].sueldo").value(1200000))
            .andExpect(jsonPath("$[0].id_sucursal").value(1));
    }

    @Test
    public void testGetAdministradorById() throws Exception {

        int id = 1;
        when(administradorService.getAdministradorId(id)).thenReturn(administrador);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/administradores/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Luis"))
            .andExpect(jsonPath("$.ap_paterno").value("Burgos"))
            .andExpect(jsonPath("$.ap_materno").value("Lavin"))
            .andExpect(jsonPath("$.correo").value("l_burgosl@perfulandia.cl"))
            .andExpect(jsonPath("$.contrasenha").value("s8d45ap/ilh*/"))
            .andExpect(jsonPath("$.num_telefono").value(954123658))
            .andExpect(jsonPath("$.sueldo").value(1200000))
            .andExpect(jsonPath("$.id_sucursal").value(1));
    }

    @Test
    public void testCreateAdministrador() throws Exception {

        when(administradorService.saveAdministrador(administrador)).thenReturn(administrador);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/administradores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administrador)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Luis"))
                .andExpect(jsonPath("$.ap_paterno").value("Burgos"))
                .andExpect(jsonPath("$.ap_materno").value("Lavin"))
                .andExpect(jsonPath("$.correo").value("l_burgosl@perfulandia.cl"))
                .andExpect(jsonPath("$.contrasenha").value("s8d45ap/ilh*/"))
                .andExpect(jsonPath("$.num_telefono").value(954123658))
                .andExpect(jsonPath("$.sueldo").value(1200000))
                .andExpect(jsonPath("$.id_sucursal").value(1));
    }

    @Test
    public void testUpdateAdministrador() throws Exception {

        int id = 1;
        administrador.setId(id);
        when(administradorService.updateAdministrador(administrador)).thenReturn(administrador);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/v1/administradores/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administrador)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.nombre").value("Luis"))
                .andExpect(jsonPath("$.ap_paterno").value("Burgos"))
                .andExpect(jsonPath("$.ap_materno").value("Lavin"))
                .andExpect(jsonPath("$.correo").value("l_burgosl@perfulandia.cl"))
                .andExpect(jsonPath("$.contrasenha").value("s8d45ap/ilh*/"))
                .andExpect(jsonPath("$.num_telefono").value(954123658))
                .andExpect(jsonPath("$.sueldo").value(1200000))
                .andExpect(jsonPath("$.id_sucursal").value(1));
    }

    @Test
    public void testDeleteAdministrador() throws Exception {
        int id = 1;

        when(administradorService.deleteAdministrador(id)).thenReturn("Administrador eliminado con éxito");
        
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/v1/administradores/" + id))
            .andExpect(status().isOk())
            .andExpect(content().string("Administrador eliminado con éxito"));

        verify(administradorService, times(1)).deleteAdministrador(id);
    }
}
