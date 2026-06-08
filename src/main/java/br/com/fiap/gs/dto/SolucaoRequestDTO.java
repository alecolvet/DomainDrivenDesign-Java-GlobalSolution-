package br.com.fiap.gs.dto;

import br.com.fiap.gs.model.AreaImpacto;
import br.com.fiap.gs.model.ODS;
import jakarta.validation.constraints.*;

public class SolucaoRequestDTO {

    @NotBlank(message = "O nome da solução é obrigatório")
    @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 10, max = 1000, message = "A descrição deve ter entre 10 e 1000 caracteres")
    private String descricao;

    @NotNull(message = "A área de impacto é obrigatória")
    private AreaImpacto areaImpacto;

    @NotNull(message = "O ODS relacionado é obrigatório")
    private ODS ods;

    @NotNull(message = "A urgência é obrigatória")
    @Min(value = 1, message = "A urgência deve ser entre 1 e 5")
    @Max(value = 5, message = "A urgência deve ser entre 1 e 5")
    private Integer urgencia;

    @NotNull(message = "O impacto é obrigatório")
    @Min(value = 1, message = "O impacto deve ser entre 1 e 5")
    @Max(value = 5, message = "O impacto deve ser entre 1 e 5")
    private Integer impacto;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public AreaImpacto getAreaImpacto() { return areaImpacto; }
    public void setAreaImpacto(AreaImpacto areaImpacto) { this.areaImpacto = areaImpacto; }

    public ODS getOds() { return ods; }
    public void setOds(ODS ods) { this.ods = ods; }

    public Integer getUrgencia() { return urgencia; }
    public void setUrgencia(Integer urgencia) { this.urgencia = urgencia; }

    public Integer getImpacto() { return impacto; }
    public void setImpacto(Integer impacto) { this.impacto = impacto; }
}
