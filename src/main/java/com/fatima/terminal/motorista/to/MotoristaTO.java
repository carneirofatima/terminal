package com.fatima.terminal.motorista.to;

import com.fatima.terminal.motorista.entity.Motorista;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotoristaTO {

    private Integer motoristaKey;

    private String nome;

    private Integer idade;

    private String genero;

    private Boolean possuiVeiculo;

    private Integer tipoCNH;

    private Boolean estaCarregado;

    private Integer tipoVeiculo;

    private String email;

    private String numeroTelefone;

    public Motorista paraDominio() {
        Motorista motorista = new Motorista();
        motorista.setEmail(this.email);
        motorista.setNome(this.nome);
        motorista.setIdade(this.idade);
        motorista.setEstaCarregado(this.estaCarregado);
        motorista.setTipoVeiculo(this.tipoVeiculo);
        motorista.setTipoCNH(this.tipoCNH);
        motorista.setGenero(this.genero);
        motorista.setNumeroTelefone(this.numeroTelefone);
        motorista.setPossuiVeiculo(this.possuiVeiculo);

        return motorista;
    }

    public static MotoristaTO builder(Motorista motorista) {
        MotoristaTO to = new MotoristaTO();
        to.setNome(motorista.getNome());
        to.setEmail(motorista.getEmail());
        to.setIdade(motorista.getIdade());
        to.setEstaCarregado(motorista.getEstaCarregado());
        to.setTipoVeiculo(motorista.getTipoVeiculo());
        to.setTipoCNH(motorista.getTipoCNH());
        to.setGenero(motorista.getGenero());
        to.setNumeroTelefone(motorista.getNumeroTelefone());
        to.setPossuiVeiculo((motorista.getPossuiVeiculo()));

        return to;
    }

}
