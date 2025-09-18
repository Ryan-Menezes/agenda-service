package pdev.com.agenda.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id) {
        var response = pacienteService.findById(id);

        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@RequestBody Paciente data) {
        var paciente = pacienteService.save(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> delete(@PathVariable Long id) {
        pacienteService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
