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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import pdev.com.agenda.api.mappers.AgendaMapper;
import pdev.com.agenda.api.requests.AgendaRequest;
import pdev.com.agenda.api.responses.AgendaResponse;
import pdev.com.agenda.domain.services.AgendaService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agendas")
public class AgendaController {
    private final AgendaService agendaService;
    private final AgendaMapper mapper;

    @PostMapping
    public ResponseEntity<AgendaResponse> save(@Valid @RequestBody AgendaRequest request) {
        var agenda = agendaService.save(mapper.toAgenda(request));
        var response = mapper.toAgendaResponse(agenda);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> findAll() {
        var agendas = agendaService.findAll();
        var response = mapper.toAgendaResponseList(agendas);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> findById(@PathVariable Long id) {
        var result = agendaService.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = mapper.toAgendaResponse(result.get());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaResponse> update(@PathVariable Long id, @Valid @RequestBody AgendaRequest request) {
        var agenda = agendaService.update(id, mapper.toAgenda(request));
        var response = mapper.toAgendaResponse(agenda);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AgendaResponse> delete(@PathVariable Long id) {
        agendaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
