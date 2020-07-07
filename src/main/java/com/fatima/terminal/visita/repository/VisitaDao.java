package com.fatima.terminal.visita.repository;

import com.fatima.terminal.motorista.entity.Motorista;
import com.fatima.terminal.visita.entity.Visita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitaDao extends JpaRepository<Visita, Integer> {

    @Query(value = "SELECT v.motorista "
                   + "FROM Visita v "
                   + "WHERE v.data between :dataInicial and :dataFinal")
    List<Motorista> consultarMotoristasQuePassaramPeloTerminal(Date dataInicial, Date dataFinal);
}
