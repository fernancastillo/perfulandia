package com.pagina.perfulandia.Gerente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name="gerente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=30, nullable=false)
    private String nombre;

    @Column(length=30, nullable=false)
    private String ap_paterno;

    @Column(length=30, nullable=false)
    private String ap_materno;

    @Column(length=80, nullable=false)
    private String correo;

    @Column(length=80, nullable=false)
    private String contrasenha;

    @Column(nullable=true)
    private int num_telefono;

    @Column(nullable=false)
    private int sueldo;

    @Column(length=80, nullable=false)
    private int id_sucursal;
}