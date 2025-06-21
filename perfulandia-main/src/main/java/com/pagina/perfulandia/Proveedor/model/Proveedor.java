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


}
