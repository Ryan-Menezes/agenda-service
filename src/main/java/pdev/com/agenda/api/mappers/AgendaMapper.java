package pdev.com.agenda.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pdev.com.agenda.api.requests.AgendaRequest;
import pdev.com.agenda.api.responses.AgendaResponse;
import pdev.com.agenda.domain.entities.Agenda;

@Component
@RequiredArgsConstructor
public class AgendaMapper {
    private final ModelMapper mapper;

    public Agenda toAgenda(AgendaRequest request) {
        return mapper.map(request, Agenda.class);
    }

    public AgendaResponse toAgendaResponse(Agenda paciente) {
        return mapper.map(paciente, AgendaResponse.class);
    }

    public List<AgendaResponse> toAgendaResponseList(List<Agenda> pacientes) {
        return pacientes.stream()
                .map(this::toAgendaResponse)
                .collect(Collectors.toList());
    }
}
