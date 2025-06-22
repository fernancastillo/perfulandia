package com.pagina.perfulandia.Venta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import jakarta.persistence.*;



@Entity
@Table(name="venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false)
    private Date fecha;

    @Column(nullable=false)
    private int id_emp;

    @Column(nullable=false)
    private int id_cli;

    @Column(nullable=false)
    private int total;

    @Column(nullable=true)
    private float descuento;




}
