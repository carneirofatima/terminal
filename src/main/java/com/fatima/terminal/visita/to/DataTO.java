package com.fatima.terminal.visita.to;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTO {

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dataInicial;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dataFinal;

}
