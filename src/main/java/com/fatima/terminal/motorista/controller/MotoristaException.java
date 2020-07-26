package com.fatima.terminal.motorista.controller;

import com.fatima.terminal.validator.ErroInternoException;
import com.fatima.terminal.validator.MotoristaInexistenteException;
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

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Motorista inexistente")
    @ExceptionHandler(MotoristaInexistenteException.class)
    public void motoristaNaoCadastrado(MotoristaInexistenteException e) {
        log.error("Este motorista não consta no sistema.", e);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Erro inesperado")
    @ExceptionHandler(ErroInternoException.class)
    public void erroInesperado(ErroInternoException e) {
        log.error("Ocorreu um erro inesperado.", e);
    }
}