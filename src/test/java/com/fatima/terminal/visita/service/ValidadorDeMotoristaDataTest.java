package com.fatima.terminal.visita.service;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import javax.xml.bind.ValidationException;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorDeMotoristaDataTest extends TestCase {

    @InjectMocks
    private ValidadorData service;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private LocalDate dataInicial;

    private LocalDate dataFinal;

    @Test
    public void deveDarErro() throws ValidationException {
        dadoUmaDataInicial();
        dadoUmaDataFinal();
        deveExibirException();
        quandoValidarData();
    }

    private void deveExibirException() {
        exception.expectMessage("A data inicial não pode ser posterior a data final.");
    }

    private void quandoValidarData() throws ValidationException {
        service.dataInicialMaiorQueDataFinal(dataInicial, dataFinal);
    }

    private void dadoUmaDataFinal() {
        dataFinal = LocalDate.of(2019, 2, 3);
    }

    private void dadoUmaDataInicial() {
        dataInicial = LocalDate.of(2020, 2, 3);
    }

}