package com.dsw.backendlupi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String nombre;
    @Column
    private boolean vigencia;
    @OneToOne
    private DocumentoAlmacenado foto;
}
