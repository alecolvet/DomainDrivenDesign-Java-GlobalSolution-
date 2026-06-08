package br.com.fiap.gs.dto;

import br.com.fiap.gs.model.StatusSolucao;
import jakarta.validation.constraints.NotNull;

public class StatusUpdateDTO {

    @NotNull(message = "O novo status é obrigatório")
    private StatusSolucao status;

    public StatusSolucao getStatus() { return status; }
    public void setStatus(StatusSolucao status) { this.status = status; }
}
