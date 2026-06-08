package br.com.fiap.gs.service;

import br.com.fiap.gs.dto.ResumoDTO;
import br.com.fiap.gs.dto.SolucaoRequestDTO;
import br.com.fiap.gs.dto.SolucaoResponseDTO;
import br.com.fiap.gs.dto.StatusUpdateDTO;
import br.com.fiap.gs.exception.RegraDeNegocioException;
import br.com.fiap.gs.exception.SolucaoNotFoundException;
import br.com.fiap.gs.model.AreaImpacto;
import br.com.fiap.gs.model.ODS;
import br.com.fiap.gs.model.SolucaoEspacial;
import br.com.fiap.gs.model.StatusSolucao;
import br.com.fiap.gs.repository.SolucaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SolucaoService {

    private final SolucaoRepository repository;

    public SolucaoService(SolucaoRepository repository) {
        this.repository = repository;
    }

    // ============================================================
    // CADASTRAR
    // ============================================================
    @Transactional
    public SolucaoResponseDTO cadastrar(SolucaoRequestDTO dto) {
        SolucaoEspacial solucao = new SolucaoEspacial(
                dto.getNome(),
                dto.getDescricao(),
                dto.getAreaImpacto(),
                dto.getOds(),
                dto.getUrgencia(),
                dto.getImpacto()
        );
        SolucaoEspacial salva = repository.save(solucao);
        return SolucaoResponseDTO.fromEntity(salva);
    }

    // ============================================================
    // LISTAR TODAS
    // ============================================================
    @Transactional(readOnly = true)
    public List<SolucaoResponseDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(SolucaoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ============================================================
    // BUSCAR POR ID
    // ============================================================
    @Transactional(readOnly = true)
    public SolucaoResponseDTO buscarPorId(Long id) {
        SolucaoEspacial solucao = buscarEntidadePorId(id);
        return SolucaoResponseDTO.fromEntity(solucao);
    }

    // ============================================================
    // BUSCAR POR ÁREA DE IMPACTO
    // ============================================================
    @Transactional(readOnly = true)
    public List<SolucaoResponseDTO> buscarPorAreaImpacto(AreaImpacto areaImpacto) {
        return repository.findByAreaImpacto(areaImpacto)
                .stream()
                .map(SolucaoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ============================================================
    // BUSCAR POR ODS
    // ============================================================
    @Transactional(readOnly = true)
    public List<SolucaoResponseDTO> buscarPorOds(ODS ods) {
        return repository.findByOds(ods)
                .stream()
                .map(SolucaoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ============================================================
    // ATUALIZAR
    // ============================================================
    @Transactional
    public SolucaoResponseDTO atualizar(Long id, SolucaoRequestDTO dto) {
        SolucaoEspacial solucao = buscarEntidadePorId(id);

        // REGRA: Não permitir alteração de solução CANCELADA
        if (solucao.getStatus() == StatusSolucao.CANCELADA) {
            throw new RegraDeNegocioException(
                "Não é possível alterar uma solução com status CANCELADA.");
        }

        solucao.setNome(dto.getNome());
        solucao.setDescricao(dto.getDescricao());
        solucao.setAreaImpacto(dto.getAreaImpacto());
        solucao.setOds(dto.getOds());
        solucao.setUrgencia(dto.getUrgencia());
        solucao.setImpacto(dto.getImpacto());

        SolucaoEspacial atualizada = repository.save(solucao);
        return SolucaoResponseDTO.fromEntity(atualizada);
    }

    // ============================================================
    // ALTERAR STATUS
    // ============================================================
    @Transactional
    public SolucaoResponseDTO alterarStatus(Long id, StatusUpdateDTO dto) {
        SolucaoEspacial solucao = buscarEntidadePorId(id);

        // REGRA: Não permitir alteração de solução CANCELADA
        if (solucao.getStatus() == StatusSolucao.CANCELADA) {
            throw new RegraDeNegocioException(
                "Não é possível alterar o status de uma solução CANCELADA.");
        }

        solucao.setStatus(dto.getStatus());
        SolucaoEspacial atualizada = repository.save(solucao);
        return SolucaoResponseDTO.fromEntity(atualizada);
    }

    // ============================================================
    // EXCLUIR
    // ============================================================
    @Transactional
    public void excluir(Long id) {
        SolucaoEspacial solucao = buscarEntidadePorId(id);

        // REGRA: Não permitir exclusão de solução VALIDADA
        if (solucao.getStatus() == StatusSolucao.VALIDADA) {
            throw new RegraDeNegocioException(
                "Não é possível excluir uma solução com status VALIDADA.");
        }

        repository.delete(solucao);
    }

    // ============================================================
    // RESUMO GERAL
    // ============================================================
    @Transactional(readOnly = true)
    public ResumoDTO obterResumo() {
        ResumoDTO resumo = new ResumoDTO();

        // Total de soluções
        resumo.setTotalSolucoes(repository.count());

        // Quantidade por status
        Map<StatusSolucao, Long> porStatus = new EnumMap<>(StatusSolucao.class);
        for (Object[] row : repository.countByStatus()) {
            porStatus.put((StatusSolucao) row[0], (Long) row[1]);
        }
        resumo.setQuantidadePorStatus(porStatus);

        // Quantidade por área de impacto
        Map<AreaImpacto, Long> porArea = new EnumMap<>(AreaImpacto.class);
        for (Object[] row : repository.countByAreaImpacto()) {
            porArea.put((AreaImpacto) row[0], (Long) row[1]);
        }
        resumo.setQuantidadePorAreaImpacto(porArea);

        // Soluções com prioridade alta (>= 12)
        List<SolucaoResponseDTO> prioridadeAlta = repository.findSolucoesPrioridadeAlta()
                .stream()
                .map(SolucaoResponseDTO::fromEntity)
                .collect(Collectors.toList());
        resumo.setSolucoesPrioridadeAlta(prioridadeAlta);

        return resumo;
    }

    // ============================================================
    // AUXILIAR PRIVADO
    // ============================================================
    private SolucaoEspacial buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new SolucaoNotFoundException(id));
    }
}
