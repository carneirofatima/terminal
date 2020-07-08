package com.fatima.terminal.motorista.controller;

import com.fatima.terminal.motorista.service.MotoristaService;
import com.fatima.terminal.motorista.to.MotoristaTO;
import com.fatima.terminal.validator.ValidadorException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/motorista")
public class MotoristaController {

    private final MotoristaService service;

    public MotoristaController(MotoristaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody MotoristaTO formulario) {
        try {
            return new ResponseEntity<>(
                service.cadastrar(formulario),
                HttpStatus.CREATED
            );
        } catch (ConstraintViolationException | ValidadorException e) {
            return new ResponseEntity<>(
                e,
                HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                "Ocorreu um erro inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@Valid @RequestBody MotoristaTO formulario) {
        try {
            return new ResponseEntity<>(
                service.editar(formulario),
                HttpStatus.CREATED
            );
        } catch (ConstraintViolationException | ValidadorException e) {
            return new ResponseEntity<>(
                e,
                HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                "Ocorreu um erro inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "/sem-carga")
    public ResponseEntity<?> consultarMotoristaSemCarga() {
        try {
            return new ResponseEntity<>(
                service.consutarMotoristaSemCarga(),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                "Ocorreu um erro inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "/com-veiculo-proprio")
    public ResponseEntity<?> consultarMotoristaComVeiculoProprio() {
        try {
            return new ResponseEntity<>(
                service.consultarMotoristaComVeiculoProprio(),
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
