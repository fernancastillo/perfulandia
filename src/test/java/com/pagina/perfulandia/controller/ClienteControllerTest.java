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

import com.pagina.perfulandia.Cliente.controller.ClienteController;
import com.pagina.perfulandia.Cliente.model.Cliente;
import com.pagina.perfulandia.Cliente.service.ClienteService;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;

    @BeforeEach
    void setUp(){
        cliente = new Cliente();
        
        cliente.setId(1);
        cliente.setNombre("Leonardo");
        cliente.setAp_paterno("Rodriguez");
        cliente.setAp_materno("Zapata");
        cliente.setCorreo("leonardorz@gmail.com");
        cliente.setContrasenha("5f45gf-6*23");
        cliente.setNum_telefono(945812655);
    }

    @Test
    public void testGetAllClientes() throws Exception{
        when(clienteService.getClientes()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/v1/clientes"))//la url de nuestra api
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Leonardo"))
            .andExpect(jsonPath("$[0].ap_paterno").value("Rodriguez"))
            .andExpect(jsonPath("$[0].ap_materno").value("Zapata"))
            .andExpect(jsonPath("$[0].correo").value("leonardorz@gmail.com"))
            .andExpect(jsonPath("$[0].contrasenha").value("5f45gf-6*23"))
            .andExpect(jsonPath("$[0].num_telefono").value(945812655));
    }

}
