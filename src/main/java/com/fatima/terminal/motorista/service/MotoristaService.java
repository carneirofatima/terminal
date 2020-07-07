package com.fatima.terminal.motorista.service;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.motorista.repository.MotoristaDao;
import com.fatima.terminal.motorista.to.MotoristaTO;
import com.fatima.terminal.motorista.validator.Validador;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class MotoristaService {

    private final MotoristaDao dao;
    private final Validador validador;
    private final VisitaService visitaService;

    public MotoristaService(MotoristaDao dao, Validador validador, VisitaService visitaService) {
        this.dao = dao;
        this.validador = validador;
        this.visitaService = visitaService;
    }

    @Transactional
    public MotoristaTO cadastrar(MotoristaTO formulario) {
        validador.validarFormulario(formulario);
        Motorista motorista = dao.save(formulario.paraDominio());
        visitaService.adicionarVisita(formulario.getEmail());
        return MotoristaTO.builder(motorista);
    }

    private List<MotoristaTO> retornarListaTO(List<Motorista> motoristas) {
        return motoristas
            .stream()
            .map(MotoristaTO::builder)
            .collect(Collectors.toList());
    }

    public List<MotoristaTO> consutarMotoristaSemCarga() {
        return retornarListaTO(dao.consultarMotoristaSemCarga());
    }

    public List<MotoristaTO> consultarMotoristaComVeiculoProprio() {
        return retornarListaTO(dao.consultarMotoristaComVeiculoProprio());
    }
}
