package pdev.com.agenda.api.requests;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull
    @Future
    private LocalDateTime horario;
    
    @NotNull
    private Long pacienteId;
}
