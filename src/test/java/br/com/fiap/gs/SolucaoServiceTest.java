package br.com.fiap.gs;

import br.com.fiap.gs.dto.SolucaoRequestDTO;
import br.com.fiap.gs.dto.SolucaoResponseDTO;
import br.com.fiap.gs.dto.StatusUpdateDTO;
import br.com.fiap.gs.exception.RegraDeNegocioException;
import br.com.fiap.gs.exception.SolucaoNotFoundException;
import br.com.fiap.gs.model.*;
import br.com.fiap.gs.repository.SolucaoRepository;
import br.com.fiap.gs.service.SolucaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SolucaoServiceTest {

    @Mock
    private SolucaoRepository repository;

    @InjectMocks
    private SolucaoService service;

    private SolucaoEspacial solucaoMock;

    @BeforeEach
    void setup() {
        solucaoMock = new SolucaoEspacial(
                "Teste", "Descrição de teste com mais de 10 chars",
                AreaImpacto.MEIO_AMBIENTE, ODS.ODS_15_VIDA_TERRESTRE, 4, 5
        );
        solucaoMock.setId(1L);
    }

    @Test
    void deveCadastrarSolucaoComSucesso() {
        SolucaoRequestDTO dto = criarRequestDTO();
        when(repository.save(any())).thenReturn(solucaoMock);

        SolucaoResponseDTO response = service.cadastrar(dto);

        assertNotNull(response);
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoSolucaoNaoEncontrada() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(SolucaoNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void naoDeveAtualizarSolucaoCancelada() {
        solucaoMock.setStatus(StatusSolucao.CANCELADA);
        when(repository.findById(1L)).thenReturn(Optional.of(solucaoMock));

        assertThrows(RegraDeNegocioException.class,
                () -> service.atualizar(1L, criarRequestDTO()));
    }

    @Test
    void naoDeveExcluirSolucaoValidada() {
        solucaoMock.setStatus(StatusSolucao.VALIDADA);
        when(repository.findById(1L)).thenReturn(Optional.of(solucaoMock));

        assertThrows(RegraDeNegocioException.class, () -> service.excluir(1L));
    }

    @Test
    void deveCalcularPrioridadeAutomaticamente() {
        // urgencia=4, impacto=5 → prioridade=20
        SolucaoRequestDTO dto = criarRequestDTO();
        SolucaoEspacial salva = new SolucaoEspacial(
                dto.getNome(), dto.getDescricao(),
                dto.getAreaImpacto(), dto.getOds(),
                dto.getUrgencia(), dto.getImpacto()
        );
        salva.setId(1L);
        // Simula @PrePersist
        salva.setPrioridade(dto.getUrgencia() * dto.getImpacto());

        when(repository.save(any())).thenReturn(salva);

        SolucaoResponseDTO response = service.cadastrar(dto);
        assertEquals(20, response.getPrioridade());
        assertEquals("CRITICA", response.getNivelPrioridade());
    }

    @Test
    void naoDeveAlterarStatusDeSolucaoCancelada() {
        solucaoMock.setStatus(StatusSolucao.CANCELADA);
        when(repository.findById(1L)).thenReturn(Optional.of(solucaoMock));

        StatusUpdateDTO dto = new StatusUpdateDTO();
        dto.setStatus(StatusSolucao.ATIVA);

        assertThrows(RegraDeNegocioException.class, () -> service.alterarStatus(1L, dto));
    }

    private SolucaoRequestDTO criarRequestDTO() {
        SolucaoRequestDTO dto = new SolucaoRequestDTO();
        dto.setNome("Solução Teste");
        dto.setDescricao("Descrição detalhada da solução de teste");
        dto.setAreaImpacto(AreaImpacto.MEIO_AMBIENTE);
        dto.setOds(ODS.ODS_15_VIDA_TERRESTRE);
        dto.setUrgencia(4);
        dto.setImpacto(5);
        return dto;
    }
}
