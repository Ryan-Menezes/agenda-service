package pdev.com.agenda.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pdev.com.agenda.domain.entities.Paciente;
import pdev.com.agenda.domain.services.PacienteService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> save(@RequestBody Paciente data) {
        var paciente = pacienteService.save(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> findAll() {
        var pacientes = pacienteService.findAll();

        return ResponseEntity.ok(pacientes);
    }
}
