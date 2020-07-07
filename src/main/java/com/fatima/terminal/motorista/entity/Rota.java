package com.fatima.terminal.motorista.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rota {

    @Id
    private String rotaKey;

    @ManyToOne
    private Motorista rotasMotorista;

    private String latitudeOrigem;

    private String longitudeOrigem;

    private String latitudeDestino;

    private String longitudeDestino;

}
