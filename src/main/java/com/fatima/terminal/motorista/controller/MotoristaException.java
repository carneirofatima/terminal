package com.fatima.terminal.motorista.controller;

import com.fatima.terminal.validator.MotoristaJaCadastradoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Log4j2
public class MotoristaException {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "O formulário não está preenchido corretamente")
    @ExceptionHandler(ConstraintViolationException.class)
    public void formularioIncorreto(ConstraintViolationException e) {
        log.error("Alguns campos do formulário não estão preenchidos corretamente.", e);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Motorista já cadastrado")
    @ExceptionHandler(MotoristaJaCadastradoException.class)
    public void motoristaJaCadastrado(MotoristaJaCadastradoException e) {
        log.error("Esse motorista já está cadastrado no sitema.", e);
    }
}