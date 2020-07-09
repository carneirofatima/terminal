package com.fatima.terminal.visita.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import javax.xml.bind.ValidationException;

@Service
public class ValidadorData {

    public void dataInicialMaiorQueDataFinal(LocalDate dataInicial, LocalDate dataFinal) throws ValidationException {
        if (dataInicial.isAfter(dataFinal)) {
            throw new ValidationException("A data inicial não pode ser posterior a data final.");
        }
    }
}
