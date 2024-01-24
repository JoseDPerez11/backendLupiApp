package com.dsw.backendlupi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int cantidad;
    @Column
    private Double precio;

    @ManyToOne
    private Producto producto;
    @ManyToOne
    private Pedido pedido;

    public String getNombre(){
        return this.producto!= null ? this.producto.getNombre() : "----";
    }

    public Double getSubTotal(){
        return this.cantidad * this.precio;
    }
}
