package com.dsw.backendlupi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String nombres;
    @Column(length = 100)   
    private String apellidoPaterno;
    @Column(length = 100)
    private String apellidoMaterno;
    @Column(length = 20)
    private String tipoDoc;
    @Column(length = 8)
    private String numDoc;
    @Column(length = 100)
    private String direccionEnvio;
    @Column(length = 100)
    private String departamento;
    @Column(length = 100)
    private String provincia;
    @Column(length = 100)
    private String distrito;
    private String telefono;

    @OneToOne
    private DocumentoAlmacenado foto;

    public String getNombreCompletoCiente(){
        return this.nombres != null && this.apellidoPaterno != null && this.apellidoMaterno != null ?
                this.nombres + " " + this.apellidoPaterno + " " + this.apellidoMaterno: "-----";
    }
}
