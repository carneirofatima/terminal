package com.fatima.terminal;

import com.fatima.terminal.validator.ErroInternoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Log4j2
public class ControllerAdviceGenerico {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "O formulário não está preenchido corretamente")
    @ExceptionHandler(ConstraintViolationException.class)
    public void formularioIncorreto(ConstraintViolationException e) {
        log.error("Alguns campos do formulário não estão preenchidos corretamente.", e);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Erro inesperado")
    @ExceptionHandler(ErroInternoException.class)
    public void erroInesperado(ErroInternoException e) {
        log.error("Ocorreu um erro inesperado.", e);
    }
}
