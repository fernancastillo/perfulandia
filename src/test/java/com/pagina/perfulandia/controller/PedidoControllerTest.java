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

import com.pagina.perfulandia.Pedido.controller.PedidoController;
import com.pagina.perfulandia.Pedido.model.Pedido;
import com.pagina.perfulandia.Pedido.service.PedidoService;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pedido pedido;

    @BeforeEach
    void setUp(){
        pedido = new Pedido();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        pedido.setId(1);

        try{
            Date fecha = sdf.parse("2025-05-17");
            pedido.setFecha(fecha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pedido.setEstado("Entregado");
        pedido.setTipo_pedido("Cliente");
        pedido.setId_receptor(1);
    }

    @Test
    public void testGetAllPedidos() throws Exception{
        when(pedidoService.getPedidos()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/v1/pedidos"))//la url de nuestra api
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].fecha").value("2025-05-17T04:00:00.000+00:00"))
            .andExpect(jsonPath("$[0].estado").value("Entregado"))
            .andExpect(jsonPath("$[0].tipo_pedido").value("Cliente"))
            .andExpect(jsonPath("$[0].id_receptor").value(1));
    }
}
