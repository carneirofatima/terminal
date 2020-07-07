package com.fatima.terminal.motorista.to;

import com.fatima.terminal.motorista.entity.Visita;

import java.time.LocalDate;
import java.time.ZoneId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitaTO {

    private MotoristaTO motorista;

    private LocalDate data;

    public Visita paraDominio() {
        Visita visita = new Visita();
        visita.setMotorista(this.motorista.paraDominio());
        visita.setData(java.util.Date.from(this.data.atStartOfDay()
                                               .atZone(ZoneId.systemDefault())
                                               .toInstant()));

        return visita;
    }
}
