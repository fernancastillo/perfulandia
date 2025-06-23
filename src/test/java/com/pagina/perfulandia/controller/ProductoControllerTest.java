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

import com.pagina.perfulandia.Producto.controller.ProductoController;
import com.pagina.perfulandia.Producto.model.Producto;
import com.pagina.perfulandia.Producto.service.ProductoService;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto;

    @BeforeEach
    void setUp(){
        producto = new Producto();
        
        producto.setId(1);
        producto.setNombre("Perfume Good Girl EDP 80Ml");
        producto.setDescripcion("Su elegante botella, con forma de tacón, es un símbolo de poder y seducción.");
        producto.setPrecio(109990);
        producto.setStock(26);
        producto.setCategoria("Perfume Mujer");
    }

    @Test
    public void testGetAllProductos() throws Exception{
        when(productoService.getProductos()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/v1/productos"))//la url de nuestra api
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Perfume Good Girl EDP 80Ml"))
            .andExpect(jsonPath("$[0].descripcion").value("Su elegante botella, con forma de tacón, es un símbolo de poder y seducción."))
            .andExpect(jsonPath("$[0].precio").value(109990))
            .andExpect(jsonPath("$[0].stock").value(26))
            .andExpect(jsonPath("$[0].categoria").value("Perfume Mujer"));            
    }
}
