package com.pagina.perfulandia.Sucursal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name="sucursal")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Sucursal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=40, nullable=false)
    private String nombre;

    @Column(length=80, nullable=false)
    private String direccion; 

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sucursal no puede estar vacío");
        }
        if (nombre.length() > 40) {
            throw new IllegalArgumentException("El nombre de la sucursal no puede tener más de 40 caracteres");
        }
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección de la sucursal no puede estar vacía");
        }
        if (direccion.length() > 80) {
            throw new IllegalArgumentException("La dirección de la sucursal no puede tener más de 80 caracteres");
        }
        this.direccion = direccion;
    }

}
