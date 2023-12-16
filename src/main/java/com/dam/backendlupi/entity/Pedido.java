package com.dam.backendlupi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
    private Date fechaCompra;

    @ManyToOne
    private Cliente cliente;
    
    @Column
    private Double monto;
    @Column
    private boolean anularPedido;

}
