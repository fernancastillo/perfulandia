package com.pagina.perfulandia.Inventario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="inventario")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false)
    private int id_producto;

    @Column(nullable=false)
    private int id_sucursal;

    @Column(nullable=false)
    private int cantidad;

    public void setId_producto(int id_producto) {
        if (id_producto <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser mayor a 0");
        }
        this.id_producto = id_producto;
    }

    public void setId_sucursal(int id_sucursal) {
        if (id_sucursal <= 0) {
            throw new IllegalArgumentException("El ID de sucursal debe ser mayor a 0");
        }
        this.id_sucursal = id_sucursal;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad de un producto debe ser mayor a 0");
        }
        this.cantidad = cantidad;
    }


}
