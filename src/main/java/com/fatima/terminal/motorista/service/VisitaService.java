package com.fatima.terminal.motorista.service;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.motorista.repository.MotoristaDao;
import com.fatima.terminal.motorista.repository.VisitaDao;
import com.fatima.terminal.motorista.to.MotoristaTO;
import com.fatima.terminal.motorista.to.VisitaTO;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitaService {

    private final VisitaDao dao;
    private final MotoristaDao motoristaDao;

    public VisitaService(VisitaDao dao, MotoristaDao motoristaDao) {
        this.dao = dao;
        this.motoristaDao = motoristaDao;
    }

    public void adicionarVisita(String email) {
        VisitaTO formulario = new VisitaTO();
        LocalDate dataVisita = LocalDate.now();
        formulario.setMotorista(buscarMotorista(email));
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

    private MotoristaTO buscarMotorista(String email) {
        return MotoristaTO.builder(motoristaDao.buscarMotorista(email));
    }

}
