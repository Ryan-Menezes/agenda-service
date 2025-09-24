package pdev.com.agenda.domain.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pdev.com.agenda.domain.entities.Agenda;
import pdev.com.agenda.domain.repositories.AgendaRepository;
import pdev.com.agenda.exceptions.BusinessException;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {
    private final AgendaRepository agendaRepository;
    private final PacienteService pacienteService;

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> findById(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda save(Agenda agenda) {
        var optPaciente = pacienteService.findById(agenda.getPaciente().getId());

        if (optPaciente.isEmpty())
            throw new BusinessException("Paciente não encontrado");

        var optAgenda =  agendaRepository.findByHorario(agenda.getHorario());

        if (optAgenda.isPresent())
            throw new BusinessException("Já existe agendamento pare este horário");

        agenda.setPaciente(optPaciente.get());
        agenda.setDataCriacao(LocalDateTime.now());

        return agendaRepository.save(agenda);
    }

    public Agenda update(Long id, Agenda agenda) {
        var result = findById(id);

        if (result.isEmpty())
            throw new BusinessException("Agenda não encontrada");

        agenda.setId(id);

        return save(agenda);
    }

    public void delete(Long id) {
        agendaRepository.deleteById(id);
    }
}
