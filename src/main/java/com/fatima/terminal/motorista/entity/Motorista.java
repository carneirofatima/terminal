package com.fatima.terminal.motorista.entity;

import com.fatima.terminal.rota.entity.Rota;
import com.fatima.terminal.visita.entity.Visita;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer motoristaKey;

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
