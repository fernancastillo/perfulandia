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

    public void setFecha(Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        }
        if (fecha.after(new Date())) {
            throw new IllegalArgumentException("La fecha no puede ser posterior a la de hoy");
        }
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede estar vacío");
        }
        if (!estado.equalsIgnoreCase("ENTREGADO")&&!estado.equalsIgnoreCase("PENDIENTE")&&!estado.equalsIgnoreCase("CANCELADO")) {
            throw new IllegalArgumentException("El estado debe ser: 'Pendiente', 'Entregado' o 'Cancelado'");
        }
        this.estado = estado;
    }

    public void setTipo_pedido(String tipo_pedido) {
        if (tipo_pedido == null || tipo_pedido.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de pedido no puede estar vacío");
        }
        if (!tipo_pedido.equalsIgnoreCase("CLIENTE")&&!estado.equalsIgnoreCase("PROVEEDOR")) {
            throw new IllegalArgumentException("El tipo de pedido debe ser: 'Cliente' o 'Proveedor'");
        }
        this.tipo_pedido = tipo_pedido;
    }

    public void setId_recetor(int id_receptor) {
        if (id_receptor <= 0) {
            throw new IllegalArgumentException("El ID del receptor debe ser mayor a 0");
        }
        this.id_receptor = id_receptor;
    }

}
