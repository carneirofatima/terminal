package com.fatima.terminal.visita.controller;

import com.fatima.terminal.visita.service.VisitaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.time.LocalDate;


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
                                                                 @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataInicial,
                                                                 @RequestParam("data-final")
                                                                 @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataFinal) {
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
