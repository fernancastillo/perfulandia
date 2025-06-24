package com.pagina.perfulandia.Vendedor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="vendedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=30, nullable=false)
    private String nombre;

    @Column(length=30, nullable=false)
    private String ap_paterno;
    
    @Column(length=30, nullable=false)
    private String ap_materno;
    
    @Column(length=30, nullable=false)
    private String correo;

    @Column(length=30, nullable=false)
    private String contrasenha;

    @Column(nullable=true)
    private Integer num_telefono;

    @Column(nullable=false)
    private int sueldo;
    
    @Column(length=80, nullable=false)
    private int id_sucursal;
    
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombre.length() > 30) {
            throw new IllegalArgumentException("El nombre no puede tener más de 30 caracteres");
        }
        this.nombre = nombre;
    }

    public void setAp_paterno(String ap_paterno) {
        if (ap_paterno == null || ap_paterno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido paterno no puede estar vacío");
        }
        if (ap_paterno.length() > 30) {
            throw new IllegalArgumentException("El apellido paterno no puede tener más de 30 caracteres");
        }
        this.ap_paterno = ap_paterno;
    }

    public void setAp_materno(String ap_materno) {
        if (ap_materno == null || ap_materno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido materno no puede estar vacío");
        }
        if (ap_materno.length() > 30) {
            throw new IllegalArgumentException("El apellido materno no puede tener más de 30 caracteres");
        }
        this.ap_materno = ap_materno;
    }

    public void setCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }
        if (correo.length() > 80) {
            throw new IllegalArgumentException("El no puede tener más de 80 caracteres");
        }
        this.correo = correo;
    }

    public void setContrasenha(String contrasenha) {
        if (contrasenha == null || contrasenha.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (contrasenha.length() < 8 || contrasenha.length() > 30) {
            throw new IllegalArgumentException("La contraseña debe tener entre 8 y 30 caracteres");
        }
        this.contrasenha = contrasenha;
    }

    public void setNum_telefono(Integer num_telefono) {
        if (num_telefono != null && (num_telefono.toString().length() != 9 )) {
            throw new IllegalArgumentException("El número de teléfono debe tener 9 dígitos");
        }
        this.num_telefono = num_telefono;
    }

    public void setSueldo(int sueldo) {
        if (sueldo < 0) {
            throw new IllegalArgumentException("El sueldo debe ser mayor a 0");
        }
        this.sueldo = sueldo;
    }

    public void setId_sucursal(int id_sucursal) {
        if (id_sucursal <= 0) {
            throw new IllegalArgumentException("El ID de sucursal debe ser mayor a 0");
        }
        this.id_sucursal = id_sucursal;
    }

}