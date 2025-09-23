package pdev.com.agenda.api.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
}
