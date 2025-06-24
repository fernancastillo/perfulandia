package com.pagina.perfulandia.Proveedor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=40, nullable=false)
    private String nombre;

    @Column(nullable=false)
    private int telefono;

    @Column(length=80, nullable=false)
    private String direccion;

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombre.length() > 40) {
            throw new IllegalArgumentException("El nombre no puede tener más de 40 caracteres");
        }
        this.nombre = nombre;
    }

    public void setTelefono(Integer telefono) {
        if (telefono == null || (telefono.toString().length() != 9 )) {
            throw new IllegalArgumentException("El número de teléfono debe tener 9 dígitos");
        }
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        }
        if (direccion.length() > 80) {
            throw new IllegalArgumentException("La dirección no puede tener más de 80 caracteres");
        }
        this.direccion = direccion;
    }

}
