package com.fatima.terminal.motorista.service;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.motorista.repository.MotoristaDao;
import com.fatima.terminal.motorista.to.MotoristaTO;
import com.fatima.terminal.motorista.validator.Validador;
import com.fatima.terminal.visita.service.VisitaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MotoristaServiceTest {

    @InjectMocks
    private MotoristaService service;

    @Mock
    private MotoristaDao dao;

    @Mock
    private Validador validador;

    @Mock
    private VisitaService visitaService;

    private MotoristaTO to;

    @Test
    public void cadastrarNovoMotorista() {
        dadoUmTO();
        quandoTentarSalvar();
        deveCadastrarUmMotorista();
    }

    @Test(expected = Exception.class)
    public void naoDeveCadastrarMotorista() {
        dadoUmTO();
        dadoUmMotoristaComMesmoEmailJaSalvo();
        deveRetornarAoTentarCadastrarException();
    }

    @Test
    public void deveConsultarMotoristasSemCarga() {
        quandoConsultarMotoristasSemCarga();
        deveTerConsultadoMotoristasNoBancoDeDados();
    }

    @Test
    public void deveConsultarMotoristasComVeiculoProprio() {
        quandoConsultarMotoristasComVeiculoProprio();
        deveConsultarMotoristasComVeiculoProprioNoBancoDeDados();
    }

    private void deveConsultarMotoristasComVeiculoProprioNoBancoDeDados() {
        verify(dao).consultarMotoristaComVeiculoProprio();
    }

    private void quandoConsultarMotoristasComVeiculoProprio() {
        service.consultarMotoristaComVeiculoProprio();
    }

    private void deveTerConsultadoMotoristasNoBancoDeDados() {
        verify(dao).consultarMotoristaSemCarga();
    }

    private void quandoConsultarMotoristasSemCarga() {
        service.consutarMotoristaSemCarga();
    }

    private void deveRetornarAoTentarCadastrarException() {
        service.cadastrar(to);
    }

    private void dadoUmMotoristaComMesmoEmailJaSalvo() {
        Motorista motoristaSalvo = new Motorista();
        motoristaSalvo.setEmail(to.getEmail());
        new ArrayList<Motorista>().add(motoristaSalvo);
    }


    private void deveCadastrarUmMotorista() {
        verify(dao).save(any());
    }

    private void quandoTentarSalvar() {
        when(dao.save(any())).thenReturn(new Motorista());
        service.cadastrar(to);
    }

    private void dadoUmTO() {
        to = new MotoristaTO();
        to.setEmail("teste@teste.com.br");
        to.setEstaCarregado(true);
        to.setGenero("Feminino");
        to.setNome("Teste da Silva");
        to.setNumeroTelefone("(11) 91234-5678");
        to.setTipoVeiculo(1);
        to.setIdade(40);
        to.setPossuiVeiculo(true);
        to.setTipoCNH(1);
    }

}