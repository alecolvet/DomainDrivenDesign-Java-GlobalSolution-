package br.com.fiap.gs.controller;

import br.com.fiap.gs.dto.ResumoDTO;
import br.com.fiap.gs.dto.SolucaoRequestDTO;
import br.com.fiap.gs.dto.SolucaoResponseDTO;
import br.com.fiap.gs.dto.StatusUpdateDTO;
import br.com.fiap.gs.model.AreaImpacto;
import br.com.fiap.gs.model.ODS;
import br.com.fiap.gs.service.SolucaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solucoes")
@Tag(name = "Soluções Espaciais", description = "API para gestão de soluções da economia espacial")
public class SolucaoController {

    private final SolucaoService service;

    public SolucaoController(SolucaoService service) {
        this.service = service;
    }

    // ============================================================
    // POST /api/solucoes - Cadastrar solução
    // ============================================================
    @PostMapping
    @Operation(summary = "Cadastrar nova solução espacial")
    public ResponseEntity<SolucaoResponseDTO> cadastrar(@Valid @RequestBody SolucaoRequestDTO dto) {
        SolucaoResponseDTO criada = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    // ============================================================
    // GET /api/solucoes - Listar todas
    // ============================================================
    @GetMapping
    @Operation(summary = "Listar todas as soluções cadastradas")
    public ResponseEntity<List<SolucaoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    // ============================================================
    // GET /api/solucoes/{id} - Buscar por ID
    // ============================================================
    @GetMapping("/{id}")
    @Operation(summary = "Buscar solução por ID")
    public ResponseEntity<SolucaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // ============================================================
    // GET /api/solucoes/area/{areaImpacto} - Buscar por área
    // ============================================================
    @GetMapping("/area/{areaImpacto}")
    @Operation(summary = "Buscar soluções por área de impacto")
    public ResponseEntity<List<SolucaoResponseDTO>> buscarPorAreaImpacto(
            @PathVariable AreaImpacto areaImpacto) {
        return ResponseEntity.ok(service.buscarPorAreaImpacto(areaImpacto));
    }

    // ============================================================
    // GET /api/solucoes/ods/{ods} - Buscar por ODS
    // ============================================================
    @GetMapping("/ods/{ods}")
    @Operation(summary = "Listar soluções por ODS relacionado")
    public ResponseEntity<List<SolucaoResponseDTO>> buscarPorOds(@PathVariable ODS ods) {
        return ResponseEntity.ok(service.buscarPorOds(ods));
    }

    // ============================================================
    // PUT /api/solucoes/{id} - Atualizar solução completa
    // ============================================================
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar solução existente (PUT completo)")
    public ResponseEntity<SolucaoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody SolucaoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    // ============================================================
    // PATCH /api/solucoes/{id}/status - Alterar só o status
    // ============================================================
    @PatchMapping("/{id}/status")
    @Operation(summary = "Alterar apenas o status da solução")
    public ResponseEntity<SolucaoResponseDTO> alterarStatus(
            @PathVariable Long id,
            @Valid @RequestBody StatusUpdateDTO dto) {
        return ResponseEntity.ok(service.alterarStatus(id, dto));
    }

    // ============================================================
    // DELETE /api/solucoes/{id} - Excluir solução
    // ============================================================
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma solução")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // ============================================================
    // GET /api/solucoes/resumo - Resumo geral
    // ============================================================
    @GetMapping("/resumo")
    @Operation(summary = "Retornar resumo geral das soluções")
    public ResponseEntity<ResumoDTO> obterResumo() {
        return ResponseEntity.ok(service.obterResumo());
    }
}
