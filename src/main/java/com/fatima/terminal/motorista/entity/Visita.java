package com.fatima.terminal.motorista.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer visitaKey;

    @ManyToOne
    private Motorista motorista;

    @Temporal(TemporalType.DATE)
    private Date data;
}
