package br.com.fiap.gs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOLUCAO_ESPACIAL")
public class SolucaoEspacial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da solução é obrigatório")
    @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 10, max = 1000, message = "A descrição deve ter entre 10 e 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String descricao;

    @NotNull(message = "A área de impacto é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AreaImpacto areaImpacto;

    @NotNull(message = "O ODS relacionado é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ODS ods;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSolucao status = StatusSolucao.PROPOSTA;

    @Min(value = 1, message = "A urgência deve ser entre 1 e 5")
    @Max(value = 5, message = "A urgência deve ser entre 1 e 5")
    @Column(nullable = false)
    private Integer urgencia;

    @Min(value = 1, message = "O impacto deve ser entre 1 e 5")
    @Max(value = 5, message = "O impacto deve ser entre 1 e 5")
    @Column(nullable = false)
    private Integer impacto;

    // Calculado automaticamente: urgencia * impacto
    @Column(nullable = false)
    private Integer prioridade;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        calcularPrioridade();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
        calcularPrioridade();
    }

    private void calcularPrioridade() {
        if (urgencia != null && impacto != null) {
            this.prioridade = urgencia * impacto;
        }
    }

    // ============================================================
    // Construtores
    // ============================================================
    public SolucaoEspacial() {}

    public SolucaoEspacial(String nome, String descricao, AreaImpacto areaImpacto,
                            ODS ods, Integer urgencia, Integer impacto) {
        this.nome = nome;
        this.descricao = descricao;
        this.areaImpacto = areaImpacto;
        this.ods = ods;
        this.urgencia = urgencia;
        this.impacto = impacto;
        this.status = StatusSolucao.PROPOSTA;
    }

    // ============================================================
    // Getters e Setters
    // ============================================================
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public AreaImpacto getAreaImpacto() { return areaImpacto; }
    public void setAreaImpacto(AreaImpacto areaImpacto) { this.areaImpacto = areaImpacto; }

    public ODS getOds() { return ods; }
    public void setOds(ODS ods) { this.ods = ods; }

    public StatusSolucao getStatus() { return status; }
    public void setStatus(StatusSolucao status) { this.status = status; }

    public Integer getUrgencia() { return urgencia; }
    public void setUrgencia(Integer urgencia) { this.urgencia = urgencia; }

    public Integer getImpacto() { return impacto; }
    public void setImpacto(Integer impacto) { this.impacto = impacto; }

    public Integer getPrioridade() { return prioridade; }
    public void setPrioridade(Integer prioridade) { this.prioridade = prioridade; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}
