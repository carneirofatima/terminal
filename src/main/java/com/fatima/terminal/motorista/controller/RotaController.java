package com.fatima.terminal.motorista.controller;

import com.fatima.terminal.motorista.service.RotaService;
import com.fatima.terminal.motorista.to.rota.RotaForm;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/rota")
public class RotaController {

    private final RotaService service;

    public RotaController(RotaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody RotaForm form) {
        try {
            service.adicionarRota(form);
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

    @GetMapping(value = "/destino")
    public ResponseEntity<?> consultarDestinos(@RequestBody String latitude,
                                               @RequestBody String longitude) {
        try {
            return new ResponseEntity<>(
                service.buscarDestinos(latitude, longitude),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                "Ocorreu um erro inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "/origem")
    public ResponseEntity<?> consultarOrigem(@RequestBody String latitude,
                                               @RequestBody String longitude) {
        try {
            return new ResponseEntity<>(
                service.buscarOrigem(latitude, longitude),
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
