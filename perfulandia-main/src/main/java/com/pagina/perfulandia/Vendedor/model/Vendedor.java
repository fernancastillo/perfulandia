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
    private int num_telefono;

    @Column(nullable=false)
    private int sueldo;
    
    @Column(length=80, nullable=false)
    private int id_sucursal;
    
}