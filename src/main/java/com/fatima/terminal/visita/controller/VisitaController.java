package com.fatima.terminal.visita.controller;

import com.fatima.terminal.validator.ErroInternoException;
import com.fatima.terminal.visita.service.VisitaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.time.LocalDate;


@RestController
@RequestMapping(value = "/visita")
public class VisitaController {

    private final VisitaService service;

    public VisitaController(VisitaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> adicionarVisita(@Valid @RequestBody String motoristaEmail) throws ConstraintViolationException,ErroInternoException {

        service.adicionarVisita(motoristaEmail);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> consultarMotoristasPassaramTerminal(@RequestParam("data-inicial")
                                                                 @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataInicial,
                                                                 @RequestParam("data-final")
                                                                 @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataFinal) throws ErroInternoException, ValidationException {
        return new ResponseEntity<>(
                service.consultarMotoristasQuePassaramPeloTerminal(dataInicial, dataFinal),
                HttpStatus.OK
        );
    }
}
