package com.fatima.terminal.motorista.controller;

import com.fatima.terminal.motorista.service.VisitaService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/visita")
public class VisitaController {

    private final VisitaService service;

    public VisitaController(VisitaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> adicionarVisita(@Valid @RequestBody String motoristaEmail) {
        try {
            service.adicionarVisita(motoristaEmail);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(
                e, HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                "Ocorreu um erro inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping
    public ResponseEntity<?> consultarMotoristasPassaramTerminal(@RequestParam("data-inicial")
                                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial,
                                                                 @RequestParam("data-final")
                                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) {
        try {
            return new ResponseEntity<>(
                service.consultarMotoristasQuePassaramPeloTerminal(dataInicial, dataFinal),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                "Ocorreu um erro inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

}
