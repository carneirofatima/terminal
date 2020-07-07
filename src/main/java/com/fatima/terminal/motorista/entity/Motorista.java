package com.fatima.terminal.motorista.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Motorista {

    @Id
    private String email;

    @OneToMany(mappedBy = "motorista")
    private List<Visita> visitas;

    @OneToMany(mappedBy = "rotasMotorista")
    private List<Rota> rotas;

    private String nome;

    private Integer idade;

    private String genero;

    private Boolean possuiVeiculo;

    private Integer tipoCNH;

    private Boolean estaCarregado;

    private Integer tipoVeiculo;

    private String numeroTelefone;
}
