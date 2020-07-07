package com.fatima.terminal.motorista.validator;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.motorista.repository.MotoristaDao;
import com.fatima.terminal.motorista.to.MotoristaTO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Validador {

    private final MotoristaDao dao;
    private final String mensagemFormularioInvalido = " Por favor, verifique se os dados estão corretos ou se o motorista já está cadastrado";

    public Validador(MotoristaDao dao) {
        this.dao = dao;
    }

    public void validarFormulario(MotoristaTO formulario) {
        validarEmail(formulario.getEmail());
        validarTelefone(formulario.getNumeroTelefone());
    }

    private void validarEmail(String email) {
        List<Motorista> motoristas = dao.consultarPorEmail(email);
        if (!motoristas.isEmpty()) {
            throw new ValidadorException("Este email já foi cadastrado" + mensagemFormularioInvalido);
        }
    }

    private void validarTelefone(String telefone) {
        List<Motorista> motoristas = dao.consultarPorTelefone(telefone);
        if (!motoristas.isEmpty()) {
            throw new ValidadorException("Este telefone já foi cadastrado" + mensagemFormularioInvalido);
        }
    }

}
