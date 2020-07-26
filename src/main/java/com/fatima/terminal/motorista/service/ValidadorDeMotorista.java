package com.fatima.terminal.motorista.service;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.motorista.repository.MotoristaDao;
import com.fatima.terminal.motorista.to.MotoristaTO;
import com.fatima.terminal.validator.MotoristaInexistenteException;
import com.fatima.terminal.validator.MotoristaJaCadastradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidadorDeMotorista {

    private final MotoristaDao dao;
    private final String mensagemFormularioInvalido = " Por favor, verifique se os dados estão corretos ou se o motorista já está cadastrado";

    public ValidadorDeMotorista(MotoristaDao dao) {
        this.dao = dao;
    }

    public void motoristaPodeSerEditado(Integer motoristaKey) {
        if (motoristaKey == null || dao.buscarMotoristaPorId(motoristaKey) == null) {
            throw new MotoristaInexistenteException("Não é possível editar um motorista que ainda não foi salvo no sistema");
        }
    }

    public void validarFormulario(MotoristaTO formulario) {
        validarEmail(formulario.getEmail());
        validarTelefone(formulario.getNumeroTelefone());
    }

    private void validarEmail(String email) {
        List<Motorista> motoristas = dao.consultarPorEmail(email);
        if (!motoristas.isEmpty()) {
            throw new MotoristaJaCadastradoException("Este email já foi cadastrado" + mensagemFormularioInvalido);
        }
    }

    private void validarTelefone(String telefone) {
        List<Motorista> motoristas = dao.consultarPorTelefone(telefone);
        if (!motoristas.isEmpty()) {
            throw new MotoristaJaCadastradoException("Este telefone já foi cadastrado" + mensagemFormularioInvalido);
        }
    }

}
