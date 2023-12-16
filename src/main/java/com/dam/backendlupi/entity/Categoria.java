package com.dam.backendlupi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
