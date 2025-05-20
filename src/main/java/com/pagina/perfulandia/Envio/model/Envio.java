package com.pagina.perfulandia.Envio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="envio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false)
    private int id_pedido;

    @Column(length=80, nullable=false)
    private Date fecha_envio;

    @Column(length=50, nullable=false)
    private String estado;
}
