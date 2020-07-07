package com.fatima.terminal.visita.to;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTO {

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime dataInicial;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime dataFinal;

}
