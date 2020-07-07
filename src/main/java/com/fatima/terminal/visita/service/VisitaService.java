package com.fatima.terminal.visita.service;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.motorista.service.MotoristaService;
import com.fatima.terminal.motorista.to.MotoristaTO;
import com.fatima.terminal.visita.repository.VisitaDao;
import com.fatima.terminal.visita.to.VisitaTO;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitaService {

    private final VisitaDao dao;
    private final MotoristaService motoristaService;

    public VisitaService(VisitaDao dao, MotoristaService motoristaService) {
        this.dao = dao;
        this.motoristaService = motoristaService;
    }

    public void adicionarVisita(String email) {
        VisitaTO formulario = new VisitaTO();
        LocalDate dataVisita = LocalDate.now();
        formulario.setMotorista(motoristaService.buscarMotorista(email));
        formulario.setData(dataVisita);
        dao.save(formulario.paraDominio());
    }

    public List<MotoristaTO> consultarMotoristasQuePassaramPeloTerminal(LocalDate dataInicial, LocalDate dataFinal) {
        Date dataInicio = converterLocalDateEmDate(dataInicial);
        Date dataFim = converterLocalDateEmDate(dataFinal);

        return retornarListaTO(dao.consultarMotoristasQuePassaramPeloTerminal(dataInicio, dataFim));
    }

    private Date converterLocalDateEmDate(LocalDate data) {
        return Date.from(data.atStartOfDay()
                             .atZone(ZoneId.systemDefault())
                             .toInstant());
    }

    private List<MotoristaTO> retornarListaTO(List<Motorista> motoristas) {
        return motoristas
            .stream()
            .map(MotoristaTO::builder)
            .collect(Collectors.toList());
    }

}
