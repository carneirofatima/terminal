package com.fatima.terminal.motorista.service;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.motorista.repository.MotoristaDao;
import com.fatima.terminal.motorista.repository.RotaDao;
import com.fatima.terminal.motorista.to.rota.RotaForm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RotaServiceTest {

    @InjectMocks
    private RotaService service;

    @Mock
    private RotaDao dao;

    @Mock
    private MotoristaDao motoristaDao;

    private RotaForm form;

    private Motorista motorista;

    private String longitude;

    private String latitude;

    @Test
    public void adicionarRota() {
        dadoUmForm();
        dadoUmMotorista();
        quandoBuscarMotoristaPeloEmail();
        quandoAdicionarNovaRota();
        deveTerChamadoOSalvar();
    }

    @Test
    public void consultarDestino() {
        dadoUmaLongitude();
        dadoUmaLatitude();
        quandoConsultarDestino();
        deveConsultarDestinos();
    }

    @Test
    public void consultarOrigem() {
        dadoUmaLongitude();
        dadoUmaLatitude();
        quandoConsultarOrigem();
        deveConsultarOrigens();
    }

    private void deveConsultarOrigens() {
        verify(dao).consultarOrigem(latitude, longitude);
    }

    private void quandoConsultarOrigem() {
        service.buscarOrigem(latitude, longitude);
    }

    private void deveConsultarDestinos() {
        verify(dao).consultarDestino(latitude, longitude);
    }

    private void quandoConsultarDestino() {
        service.buscarDestinos(latitude, longitude);
    }

    private void dadoUmaLatitude() {
        latitude = "431'2";
    }

    private void dadoUmaLongitude() {
        longitude = "'1233";
    }

    private void dadoUmMotorista() {
        motorista = new Motorista();
        motorista.setEmail(form.getEmail());
    }

    private void deveTerChamadoOSalvar() {
        verify(dao).save(any());
    }

    private void quandoBuscarMotoristaPeloEmail() {
        when(motoristaDao.buscarMotorista(form.getEmail())).thenReturn(motorista);
    }

    private void quandoAdicionarNovaRota() {
        service.adicionarRota(form);
    }

    private void dadoUmForm() {
        form = new RotaForm();
        form.setEmail("teste@teste.com.br");
        form.setDestinoLatitude("0'123");
        form.setDestinoLongitude("0'321");
        form.setOrigemLatitude("3'210");
        form.setOrigemLongitude("3'012");
    }
}