package br.com.fiap.gs.dto;

import br.com.fiap.gs.model.AreaImpacto;
import br.com.fiap.gs.model.StatusSolucao;

import java.util.List;
import java.util.Map;

public class ResumoDTO {

    private long totalSolucoes;
    private Map<StatusSolucao, Long> quantidadePorStatus;
    private Map<AreaImpacto, Long> quantidadePorAreaImpacto;
    private List<SolucaoResponseDTO> solucoesPrioridadeAlta;

    public ResumoDTO() {}

    public long getTotalSolucoes() { return totalSolucoes; }
    public void setTotalSolucoes(long totalSolucoes) { this.totalSolucoes = totalSolucoes; }

    public Map<StatusSolucao, Long> getQuantidadePorStatus() { return quantidadePorStatus; }
    public void setQuantidadePorStatus(Map<StatusSolucao, Long> quantidadePorStatus) { this.quantidadePorStatus = quantidadePorStatus; }

    public Map<AreaImpacto, Long> getQuantidadePorAreaImpacto() { return quantidadePorAreaImpacto; }
    public void setQuantidadePorAreaImpacto(Map<AreaImpacto, Long> quantidadePorAreaImpacto) { this.quantidadePorAreaImpacto = quantidadePorAreaImpacto; }

    public List<SolucaoResponseDTO> getSolucoesPrioridadeAlta() { return solucoesPrioridadeAlta; }
    public void setSolucoesPrioridadeAlta(List<SolucaoResponseDTO> solucoesPrioridadeAlta) { this.solucoesPrioridadeAlta = solucoesPrioridadeAlta; }
}
