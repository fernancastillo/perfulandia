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


}
