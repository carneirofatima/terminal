package com.fatima.terminal.motorista.validator;

import lombok.Getter;

@Getter
public class ValidadorException extends IllegalArgumentException {

    private final String mensagem;

    public ValidadorException(String message) {
        super(message);
        this.mensagem = message;
    }
}
