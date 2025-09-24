package pdev.com.agenda.api.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O sobrenome é obrigatório")
    private String sobrenome;

    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email é obrigatório")
    private String email;
    
    @NotBlank(message = "O cpf é obrigatório")
    private String cpf;
}
