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

    @Test
    public void testGetPedidoById() throws Exception {

        int id = 1;
        when(pedidoService.getPedidoId(id)).thenReturn(pedido);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/pedidos/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.fecha").value("2025-05-17T04:00:00.000+00:00"))
            .andExpect(jsonPath("$.estado").value("Entregado"))
            .andExpect(jsonPath("$.tipo_pedido").value("Cliente"))
            .andExpect(jsonPath("$.id_receptor").value(1));
          
    }

    @Test
    public void testCreatePedido() throws Exception {

        when(pedidoService.savePedido(pedido)).thenReturn(pedido);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fecha").value("2025-05-17T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.estado").value("Entregado"))
                .andExpect(jsonPath("$.tipo_pedido").value("Cliente"))
                .andExpect(jsonPath("$.id_receptor").value(1));
    }

    @Test
    public void testUpdatePedido() throws Exception {

        int id = 1;
        pedido.setId(id);
        when(pedidoService.updatePedido(pedido)).thenReturn(pedido);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/v1/pedidos/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.fecha").value("2025-05-17T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.estado").value("Entregado"))
                .andExpect(jsonPath("$.tipo_pedido").value("Cliente"))
                .andExpect(jsonPath("$.id_receptor").value(1));
    }

    @Test
    public void testDeletePedido() throws Exception {
        int id = 1;

        when(pedidoService.deletePedido(id)).thenReturn("Pedido eliminado con éxito");
        
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/v1/pedidos/" + id))
            .andExpect(status().isOk())
            .andExpect(content().string("Pedido eliminado con éxito"));

        verify(pedidoService, times(1)).deletePedido(id);
    }
}
