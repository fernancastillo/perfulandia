package com.pagina.perfulandia.Pedido.model;

import java.util.Date;

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
@Table(name="pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=80, nullable=false)
    private Date fecha;
    
    @Column(length=30, nullable=false)
    private String estado;
    
    @Column(length=30, nullable=false)
    private String tipo_pedido;

    @Column(nullable=false)
    private int id_receptor;

}
