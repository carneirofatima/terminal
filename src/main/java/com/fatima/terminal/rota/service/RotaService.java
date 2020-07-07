package com.fatima.terminal.rota.service;

import com.fatima.terminal.motorista.service.MotoristaService;
import com.fatima.terminal.rota.entity.Rota;
import com.fatima.terminal.rota.repository.RotaDao;
import com.fatima.terminal.rota.to.RotaForm;
import com.fatima.terminal.rota.to.RotaTO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RotaService {

    private final RotaDao dao;
    private final MotoristaService motoristaService;

    public RotaService(RotaDao dao, MotoristaService motoristaService) {
        this.dao = dao;
        this.motoristaService = motoristaService;
    }

    public void adicionarRota(RotaForm form) {
        RotaTO to = transformarRotaEmTO(form);
        dao.save(to.paraDominio());
    }

    public List<RotaTO> buscarDestinos(String latitude, String longitude) {
        List<Rota> rotas = dao.consultarDestino(latitude, longitude);
        return retornarListaTO(rotas);
    }

    public List<RotaTO> buscarOrigem(String latitude, String longitude) {
        List<Rota> rotas = dao.consultarOrigem(latitude, longitude);
        return retornarListaTO(rotas);
    }

    private List<RotaTO> retornarListaTO(List<Rota> rotas) {
        return rotas
            .stream()
            .map(RotaTO::builder)
            .collect(Collectors.toList());
    }

    private RotaTO transformarRotaEmTO(RotaForm form) {
        RotaTO to = new RotaTO();

        to.setMotorista(motoristaService.buscarMotorista(form.getEmail()));
        to.setLatitudeDestino(form.getDestinoLatitude());
        to.setLongitudeDestino(form.getDestinoLongitude());
        to.setLatitudeOrigem(form.getOrigemLatitude());
        to.setLongitudeOrigem(form.getOrigemLongitude());

        return to;
    }
}
