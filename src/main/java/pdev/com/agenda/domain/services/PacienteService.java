package pdev.com.agenda.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pdev.com.agenda.domain.entities.Paciente;
import pdev.com.agenda.domain.repositories.PacienteRepository;
import pdev.com.agenda.exceptions.BusinessException;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public Paciente save(Paciente paciente) {
        var responseCpf = pacienteRepository.findByCpf(paciente.getCpf());
        var responseEmail = pacienteRepository.findByEmail(paciente.getEmail());

        if (responseCpf.isPresent() && !responseCpf.get().getId().equals(paciente.getId()))
            throw new BusinessException("Cpf já cadastrado");

        if (responseEmail.isPresent() && !responseEmail.get().getId().equals(paciente.getId()))
            throw new BusinessException("E-mail já cadastrado");

        pacienteRepository.save(paciente);

        return paciente;
    }

    public Paciente update(Long id, Paciente paciente) {
        var result = findById(id);

        if (result.isEmpty())
            throw new BusinessException("Paciente não encontrado");

        paciente.setId(id);

        return save(paciente);
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
