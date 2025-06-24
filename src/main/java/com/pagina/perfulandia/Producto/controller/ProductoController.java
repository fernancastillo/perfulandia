package com.pagina.perfulandia.Producto.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pagina.perfulandia.Producto.model.Producto;
import com.pagina.perfulandia.Producto.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Producto", description = "CRUD de los productos de Perfulandia")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description =  "Obtiene una lista de todos los productos de la base de datos de Perfulandia")
    public List<Producto> listarProductos(){
        return productoService.getProductos();
    }

    @PostMapping
    @Operation(summary = "Agregar un producto", description =  "Agrega un producto a la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Producto agregado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Producto ya existe")
    }) 
    public Producto agregarProducto(@RequestBody Producto producto){
        return productoService.saveProducto(producto);    
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un producto", description =  "Busca un producto en la base de datos de Perfulandia por su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    }) 
    public Producto buscaProducto(@PathVariable int id){
        return productoService.getProductoId(id);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description =  "Actualiza un producto en la base de datos de Perfulandia")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",  
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    }) 
    public Producto actualizaProducto(@PathVariable int id, @RequestBody Producto producto){
        producto.setId(id);
        return productoService.updateProducto(producto);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description =  "Elimina un producto de la base de datos de Perfulandia con su ID")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),  
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    }) 
    public String eliminarProducto(@PathVariable int id){
        return productoService.deleteProducto(id);
    }


}
