package pdev.com.agenda.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pdev.com.agenda.domain.entities.Paciente;
import pdev.com.agenda.domain.repositories.PacienteRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public Paciente save(Paciente paciente) {
        pacienteRepository.save(paciente);

        return paciente;
    }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    public void delete(Long id) {
        pacienteRepository.deleteById(id);
    }
}
