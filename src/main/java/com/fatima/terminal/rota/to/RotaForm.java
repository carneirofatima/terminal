package com.fatima.terminal.rota.to;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RotaForm {

    @NotNull
    private String email;

    @NotNull
    private String origemLongitude;

    @NotNull
    private String origemLatitude;

    @NotNull
    private String destinoLongitude;

    @NotNull
    private String destinoLatitude;

}
