package com.fatima.terminal.validator;

import lombok.Getter;

@Getter
public class MotoristaInexistenteException extends IllegalArgumentException {
    public MotoristaInexistenteException(String message) {
        super(message);
    }
}