package com.fatima.terminal.motorista.validator;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.motorista.repository.MotoristaDao;
import com.fatima.terminal.motorista.to.MotoristaTO;
import com.fatima.terminal.validator.ValidadorException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorTest {

    @InjectMocks
    private Validador validador;

    @Mock
    private MotoristaDao dao;

    private MotoristaTO to;

    private List<Motorista> motoristasComEmailJaCadastrado;

    private List<Motorista> motoristasComTelefoneJaCadastrado;

    private Motorista motoristaCadastrado;

    @Test
    public void validarFormularioCorreto() {
        dadoUmTO();
        dadoListasComMotoristasJaCadastrados();
        dadoQueOMotoristaAindaNaoFoiCadastrado();
        quandoValidarTO();
        deveTerChamadoDao();
    }

    @Test(expected = ValidadorException.class)
    public void validarFormularioComTelefoneJaCadastrado() {
        dadoUmTO();
        dadoListasComMotoristasJaCadastrados();
        dadoQueOTelefoneJaConstaNoSistema();
        quandoValidarTO();
    }

    @Test(expected = ValidadorException.class)
    public void validarFormularioComEmailJaCadastrado() {
        dadoUmTO();
        dadoListasComMotoristasJaCadastrados();
        dadoQueOEmailJaConstaNoSistema();
        quandoValidarTO();
    }

    private void dadoQueOEmailJaConstaNoSistema() {
        motoristaCadastrado = new Motorista();
        motoristaCadastrado.setEmail(to.getEmail());
        motoristasComEmailJaCadastrado.add(motoristaCadastrado);
        when(dao.consultarPorTelefone(any())).thenReturn(motoristasComEmailJaCadastrado);
    }

    private void dadoListasComMotoristasJaCadastrados() {
        motoristasComEmailJaCadastrado = new ArrayList<>();
        motoristasComTelefoneJaCadastrado = new ArrayList<>();
    }

    private void dadoQueOTelefoneJaConstaNoSistema() {
        motoristaCadastrado = new Motorista();
        motoristaCadastrado.setNumeroTelefone(to.getNumeroTelefone());
        motoristasComTelefoneJaCadastrado.add(motoristaCadastrado);
        when(dao.consultarPorTelefone(any())).thenReturn(motoristasComTelefoneJaCadastrado);

    }

    private void deveTerChamadoDao() {
        verify(dao).consultarPorEmail(to.getEmail());
        verify(dao).consultarPorTelefone(to.getNumeroTelefone());
    }

    private void dadoQueOMotoristaAindaNaoFoiCadastrado() {
        when(dao.consultarPorEmail(to.getEmail())).thenReturn(motoristasComEmailJaCadastrado);
        when(dao.consultarPorTelefone(to.getNumeroTelefone())).thenReturn(motoristasComTelefoneJaCadastrado);
    }

    private void quandoValidarTO() {
        validador.validarFormulario(to);
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