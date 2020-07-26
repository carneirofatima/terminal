package com.fatima.terminal.rota.controller;

import com.fatima.terminal.rota.service.RotaService;
import com.fatima.terminal.rota.to.RotaForm;
import com.fatima.terminal.validator.ErroInternoException;
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
    public ResponseEntity<?> adicionar(@Valid @RequestBody RotaForm form) throws ConstraintViolationException, ErroInternoException {
        service.adicionarRota(form);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/destino")
    public ResponseEntity<?> consultarDestinos(@RequestBody String latitude,
                                               @RequestBody String longitude) throws ErroInternoException {
        return new ResponseEntity<>(service.buscarDestinos(latitude, longitude), HttpStatus.OK);
    }

    @GetMapping(value = "/origem")
    public ResponseEntity<?> consultarOrigem(@RequestBody String latitude,
                                             @RequestBody String longitude) throws ErroInternoException {
        return new ResponseEntity<>(service.buscarOrigem(latitude, longitude), HttpStatus.OK);
    }

}
