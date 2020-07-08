package com.fatima.terminal.visita.service;

import com.fatima.terminal.visita.to.DataTO;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import javax.xml.bind.ValidationException;

@Service
public class ValidadorData {

    public void dataInicialMenorQueDataFinal(LocalDate dataInicial, LocalDate dataFinal) throws ValidationException {
        if(dataInicial.isAfter(dataFinal)) {
            throw new ValidationException("A data inicial não pode ser posterior a data final.");
        }
    }
}
