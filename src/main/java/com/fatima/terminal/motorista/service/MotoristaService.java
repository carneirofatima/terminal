package com.fatima.terminal.motorista.service;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.motorista.repository.MotoristaDao;
import com.fatima.terminal.motorista.to.MotoristaTO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoristaService {

    private final MotoristaDao dao;
    private final ValidadorDeMotorista validadorDeMotorista;

    public MotoristaService(MotoristaDao dao, ValidadorDeMotorista validadorDeMotorista) {
        this.dao = dao;
        this.validadorDeMotorista = validadorDeMotorista;
    }


    public MotoristaTO cadastrar(MotoristaTO formulario) {
        validadorDeMotorista.validarFormulario(formulario);
        Motorista motorista = dao.save(formulario.paraDominio());
        return MotoristaTO.builder(motorista);
    }

    public MotoristaTO buscarMotorista(String email) {
        return MotoristaTO.builder(dao.buscarMotoristaPorEmail(email));
    }

    public List<MotoristaTO> consutarMotoristaSemCarga() {
        return retornarListaTO(dao.consultarMotoristaSemCarga());
    }

    public List<MotoristaTO> consultarMotoristaComVeiculoProprio() {
        return retornarListaTO(dao.consultarMotoristaComVeiculoProprio());
    }

    public MotoristaTO editar(MotoristaTO formulario) {
        validadorDeMotorista.motoristaPodeSerEditado(formulario.getMotoristaKey());
        Motorista motorista = dao.buscarMotoristaPorId(formulario.getMotoristaKey());
        validadorDeMotorista.validarFormulario(formulario);
        dao.save(formulario.paraDominio());
        return MotoristaTO.builder(motorista);
    }

    private List<MotoristaTO> retornarListaTO(List<Motorista> motoristas) {
        return motoristas
            .stream()
            .map(MotoristaTO::builder)
            .collect(Collectors.toList());
    }
}
