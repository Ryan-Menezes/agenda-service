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
import pdev.com.agenda.api.mappers.PacienteMapper;
import pdev.com.agenda.api.requests.PacienteRequest;
import pdev.com.agenda.api.responses.PacienteResponse;
import pdev.com.agenda.domain.services.PacienteService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;
    private final PacienteMapper mapper;

    @PostMapping
    public ResponseEntity<PacienteResponse> save(@RequestBody PacienteRequest request) {
        var paciente = pacienteService.save(mapper.toPaciente(request));
        var response = mapper.toPacienteResponse(paciente);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> findAll() {
        var pacientes = pacienteService.findAll();
        var response = mapper.toPacienteResponseList(pacientes);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> findById(@PathVariable Long id) {
        var result = pacienteService.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = mapper.toPacienteResponse(result.get());

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<PacienteResponse> update(@RequestBody PacienteRequest request) {
        var paciente = pacienteService.save(mapper.toPaciente(request));
        var response = mapper.toPacienteResponse(paciente);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteResponse> delete(@PathVariable Long id) {
        pacienteService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
