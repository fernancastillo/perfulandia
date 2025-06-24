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
    private Float descuento;

    public void setFecha(Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        }
        if (fecha.after(new Date())) {
            throw new IllegalArgumentException("La fecha no puede ser posterior a la de hoy");
        }
        this.fecha = fecha;
    }

    public void setId_emp(int id_emp) {
        if (id_emp <= 0) {
            throw new IllegalArgumentException("El ID del empleado debe ser mayor a 0");
        }
        this.id_emp = id_emp;
    }

    public void setId_cli(int id_cli) {
        if (id_cli <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser mayor a 0");
        }
        this.id_cli = id_cli;
    }

    public void setTotal(int total) {
        if (total <= 0) {
            throw new IllegalArgumentException("El total debe ser mayor a 0");
        }
        this.total = total;
    }

    public void setDescuento(Float descuento) {
        if (descuento != null && descuento < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo");
            }
        this.descuento = descuento;
    }

}
