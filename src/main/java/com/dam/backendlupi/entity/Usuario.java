package com.dam.backendlupi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500)
    private String email;
    @Column(length = 20)
    private String clave;
    @Column
    private boolean vigencia;

    @OneToOne
    private Cliente cliente;
}
