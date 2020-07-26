package com.fatima.terminal.motorista.controller;

import com.fatima.terminal.motorista.service.MotoristaService;
import com.fatima.terminal.motorista.to.MotoristaTO;
import com.fatima.terminal.validator.ErroInternoException;
import com.fatima.terminal.validator.MotoristaInexistenteException;
import com.fatima.terminal.validator.MotoristaJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> cadastrar(@Valid @RequestBody MotoristaTO formulario) throws
            ConstraintViolationException,
            MotoristaJaCadastradoException,
            ErroInternoException {
        return new ResponseEntity<>(service.cadastrar(formulario), HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<?> editar(@Valid @RequestBody MotoristaTO formulario) throws
            ConstraintViolationException,
            MotoristaInexistenteException,
            ErroInternoException {
        return new ResponseEntity<>(service.editar(formulario), HttpStatus.CREATED);
    }

    @GetMapping(value = "/sem-carga")
    public ResponseEntity<?> consultarMotoristaSemCarga() throws ErroInternoException {
        return new ResponseEntity<>(service.consutarMotoristaSemCarga(), HttpStatus.OK);
    }

    @GetMapping(value = "/com-veiculo-proprio")
    public ResponseEntity<?> consultarMotoristaComVeiculoProprio() throws ErroInternoException {
        return new ResponseEntity<>(service.consultarMotoristaComVeiculoProprio(), HttpStatus.OK);
    }

}
