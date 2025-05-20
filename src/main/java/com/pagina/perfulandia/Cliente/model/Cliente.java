package com.pagina.perfulandia.Cliente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name="cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
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

    @Column(nullable=false)
    private String contrasenha;

    @Column(nullable=true)
    private int num_telefono;
}
