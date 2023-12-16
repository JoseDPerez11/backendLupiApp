package com.dam.backendlupi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String nombre;
    @OneToOne
    private DocumentoAlmacenado foto;
    @Column
    private Double precio;
    @Column
    private int stock;
    @Column(length = 500)
    private String descripcionProducto;
    @OneToOne
    private Categoria categoria;
    @Column
    private boolean vigencia;
    @Column
    private boolean recomendado;

}
