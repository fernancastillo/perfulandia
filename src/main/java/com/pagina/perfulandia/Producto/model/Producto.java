package com.pagina.perfulandia.Producto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name="producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=50, nullable=false)
    private String nombre;

    @Column(length=80, nullable=false)
    private String descripcion;

    @Column(nullable=false)
    private int precio;

    @Column(nullable=false)
    private int stock;

    @Column(length=80, nullable=false)
    private String categoria;

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede tener más de 50 caracteres");
        }
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (descripcion.length() > 80) {
            throw new IllegalArgumentException("La descripción no puede tener más de 80 caracteres");
        }
        this.descripcion = descripcion;
    }

    public void setPrecio(int precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stock = stock;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        }
        if (!categoria.equalsIgnoreCase("PERFUME MUJER")&&!categoria.equalsIgnoreCase("PERFUME HOMBRE")&&categoria.equalsIgnoreCase("SET")) {
            throw new IllegalArgumentException("La categoria debe ser: 'Perfume mujer', 'Perfume hombre' o 'Set'");
        }
        this.categoria = categoria;
    }

}
