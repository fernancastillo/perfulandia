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

    @Column(length=30, nullable=false)
    private String nombre;

    @Column(length=80, nullable=false)
    private String descripcion;

    @Column(nullable=false)
    private int precio;

    @Column(nullable=false)
    private int stock;

    @Column(length=80, nullable=false)
    private String categoria;
}
