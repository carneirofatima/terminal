package com.fatima.terminal.rota.to;

import com.fatima.terminal.rota.entity.Rota;
import com.fatima.terminal.motorista.to.MotoristaTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RotaTO {

    private MotoristaTO motorista;

    private String latitudeOrigem;

    private String longitudeOrigem;

    private String latitudeDestino;

    private String longitudeDestino;

    public Rota paraDominio() {
        Rota rota = new Rota();
        rota.setRotasMotorista(this.motorista.paraDominio());
        rota.setLatitudeDestino(this.latitudeDestino);
        rota.setLatitudeOrigem(this.latitudeOrigem);
        rota.setLongitudeOrigem(this.longitudeOrigem);
        rota.setLongitudeOrigem(this.longitudeOrigem);

        return rota;
    }

    public static RotaTO builder(Rota rota) {
        RotaTO to = new RotaTO();
        to.setLatitudeDestino(rota.getLatitudeDestino());
        to.setLatitudeOrigem(rota.getLatitudeOrigem());
        to.setLongitudeDestino(rota.getLongitudeDestino());
        to.setLongitudeOrigem(rota.getLongitudeOrigem());

        return to;
    }

}
