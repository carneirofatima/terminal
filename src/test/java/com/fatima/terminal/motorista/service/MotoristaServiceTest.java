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

import static org.junit.Assert.assertEquals;
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

    private String email;

    private Motorista motorista;

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

    @Test
    public void deveBuscarMotorista() {
        dadoUmEmail();
        dadoUmMotorista();
        quandoBuscarMotorista();
        deveTerBuscadoMotorista();
    }

    @Test
    public void editarMotorista() {
        dadoUmEmail();
        dadoUmMotorista();
        dadoUmFormularioDeEdicao();
        quandoEditarMotorista();
        deveTerEditadoCadastroDeMotorista();
    }

    private void deveTerEditadoCadastroDeMotorista() {
        assertEquals("edicao@teste.com.br", motorista.getEmail());
    }

    private void quandoEditarMotorista() {
        motorista = to.paraDominio();
        when(dao.buscarMotoristaPorId(to.getMotoristaKey())).thenReturn(motorista);
        service.editar(to);
    }

    private void dadoUmFormularioDeEdicao() {
        to = new MotoristaTO();
        to.setEmail("edicao@teste.com.br");
        to.setMotoristaKey(1);
        to.setEstaCarregado(true);
        to.setGenero("Feminino");
        to.setNome("Teste da Silva");
        to.setNumeroTelefone("(11) 91234-5678");
        to.setTipoVeiculo(1);
        to.setIdade(40);
        to.setPossuiVeiculo(true);
        to.setTipoCNH(1);
    }

    private void dadoUmMotorista() {
        motorista = new Motorista();
        motorista.setEmail(email);
        motorista.setMotoristaKey(1);
    }

    private void deveTerBuscadoMotorista() {
        verify(dao).buscarMotoristaPorEmail(email);
    }

    private void quandoBuscarMotorista() {
        when(dao.buscarMotoristaPorEmail(email)).thenReturn(motorista);
        service.buscarMotorista(email);
    }

    private void dadoUmEmail() {
        email = "teste@teste.com.br";
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