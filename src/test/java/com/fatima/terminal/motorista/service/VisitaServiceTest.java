package com.fatima.terminal.motorista.service;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.motorista.repository.MotoristaDao;
import com.fatima.terminal.motorista.repository.VisitaDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VisitaServiceTest {

    @InjectMocks
    private VisitaService service;

    @Mock
    private VisitaDao dao;

    @Mock
    private MotoristaDao motoristaDao;

    private String email;

    private Motorista motorista;

    private LocalDate dataInicial;
    private LocalDate dataFinal;

    private Date dataInicialConvertida;
    private Date dataFinalConvertida;

    @Test
    public void adicionarVisita() {
        dadoUmEmail();
        dadoUmMotorista();
        quandoBuscarMotorista();
        quandoAdicionarVisita();
        deveTerSalvoUmaVisita();
    }

    @Test
    public void consultarMotoristasQuePassaramPeloTerminal() {
        dadoUmaDataInicial();
        dadoUmaDataFinal();
        quandoConsultarUtilizandoDatas();
        devePassarUmaDataConvertida();
        deveConsultarMotoristas();
    }

    private void devePassarUmaDataConvertida() {
        dataInicialConvertida = Date.from(dataInicial.atStartOfDay()
                                              .atZone(ZoneId.systemDefault())
                                              .toInstant());

        dataFinalConvertida = Date.from(dataFinal.atStartOfDay()
                                            .atZone(ZoneId.systemDefault())
                                            .toInstant());
    }


    private void deveConsultarMotoristas() {
        verify(dao).consultarMotoristasQuePassaramPeloTerminal(dataInicialConvertida, dataFinalConvertida);
    }

    private void quandoConsultarUtilizandoDatas() {
        service.consultarMotoristasQuePassaramPeloTerminal(dataInicial, dataFinal);
    }

    private void dadoUmaDataFinal() {
        dataFinal = LocalDate.of(2020, 1, 10);
    }

    private void dadoUmaDataInicial() {
        dataInicial = LocalDate.of(2019, 12, 10);
    }

    private void deveTerSalvoUmaVisita() {
        verify(dao).save(ArgumentMatchers.any());
    }

    private void quandoAdicionarVisita() {
        service.adicionarVisita(email);
    }

    private void dadoUmMotorista() {
        motorista = new Motorista();
        motorista.setEmail(email);
    }

    private void quandoBuscarMotorista() {
        when(motoristaDao.buscarMotorista(email)).thenReturn(motorista);
    }

    private void dadoUmEmail() {
        email = "teste@teste.com.br";
    }

}