package com.fatima.terminal.rota.entity;

import com.fatima.terminal.motorista.entity.Motorista;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String rotaKey;

    @ManyToOne
    private Motorista rotasMotorista;

    private String latitudeOrigem;

    private String longitudeOrigem;

    private String latitudeDestino;

    private String longitudeDestino;

}
