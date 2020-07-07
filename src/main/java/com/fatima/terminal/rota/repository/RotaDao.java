package com.fatima.terminal.rota.repository;

import com.fatima.terminal.rota.entity.Rota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotaDao extends JpaRepository<Rota, Integer> {

    @Query(value = "SELECT r "
                   + "FROM  Rota r "
                   + "WHERE r.latitudeDestino = :latitude"
                   + "  AND r.longitudeDestino = :longitude")
    List<Rota> consultarDestino(String latitude, String longitude);

    @Query(value = "SELECT r "
                   + "FROM  Rota r "
                   + "WHERE r.latitudeDestino = :latitude"
                   + "  AND r.longitudeDestino = :longitude")
    List<Rota> consultarOrigem(String latitude, String longitude);
}
