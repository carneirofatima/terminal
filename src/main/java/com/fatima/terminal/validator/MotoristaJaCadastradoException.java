package com.fatima.terminal.validator;

import lombok.Getter;

@Getter
public class MotoristaJaCadastradoException extends IllegalArgumentException {
    public MotoristaJaCadastradoException(String message) {
        super(message);
    }
}