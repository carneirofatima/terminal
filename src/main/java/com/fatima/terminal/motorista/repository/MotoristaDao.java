package com.fatima.terminal.motorista.repository;

import com.fatima.terminal.motorista.entity.Motorista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoristaDao extends JpaRepository<Motorista, String> {

    @Query(value = "SELECT m FROM Motorista m WHERE m.email = :email")
    List<Motorista> consultarPorEmail(String email);

    @Query(value = "SELECT m FROM Motorista m WHERE m.numeroTelefone = :numeroTelefone")
    List<Motorista> consultarPorTelefone(String numeroTelefone);

    @Query(value = "SELECT m FROM Motorista m WHERE m.estaCarregado = false")
    List<Motorista> consultarMotoristaSemCarga();

    @Query(value = "SELECT m FROM Motorista m WHERE m.possuiVeiculo = true")
    List<Motorista> consultarMotoristaComVeiculoProprio();

    @Query(value = "SELECT m FROM Motorista m WHERE m.email = :email")
    Motorista buscarMotorista(String email);
}
