package br.com.fiap.gs.dto;

import br.com.fiap.gs.model.AreaImpacto;
import br.com.fiap.gs.model.ODS;
import br.com.fiap.gs.model.SolucaoEspacial;
import br.com.fiap.gs.model.StatusSolucao;

import java.time.LocalDateTime;

public class SolucaoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private AreaImpacto areaImpacto;
    private ODS ods;
    private StatusSolucao status;
    private Integer urgencia;
    private Integer impacto;
    private Integer prioridade;
    private String nivelPrioridade;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    public SolucaoResponseDTO() {}

    public static SolucaoResponseDTO fromEntity(SolucaoEspacial s) {
        SolucaoResponseDTO dto = new SolucaoResponseDTO();
        dto.id = s.getId();
        dto.nome = s.getNome();
        dto.descricao = s.getDescricao();
        dto.areaImpacto = s.getAreaImpacto();
        dto.ods = s.getOds();
        dto.status = s.getStatus();
        dto.urgencia = s.getUrgencia();
        dto.impacto = s.getImpacto();
        dto.prioridade = s.getPrioridade();
        dto.nivelPrioridade = calcularNivelPrioridade(s.getPrioridade());
        dto.dataCadastro = s.getDataCadastro();
        dto.dataAtualizacao = s.getDataAtualizacao();
        return dto;
    }

    private static String calcularNivelPrioridade(Integer prioridade) {
        if (prioridade == null) return "INDEFINIDA";
        if (prioridade >= 20) return "CRITICA";
        if (prioridade >= 12) return "ALTA";
        if (prioridade >= 6)  return "MEDIA";
        return "BAIXA";
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public AreaImpacto getAreaImpacto() { return areaImpacto; }
    public ODS getOds() { return ods; }
    public StatusSolucao getStatus() { return status; }
    public Integer getUrgencia() { return urgencia; }
    public Integer getImpacto() { return impacto; }
    public Integer getPrioridade() { return prioridade; }
    public String getNivelPrioridade() { return nivelPrioridade; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
}
