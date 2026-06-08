package br.com.fiap.gs.repository;

import br.com.fiap.gs.model.AreaImpacto;
import br.com.fiap.gs.model.ODS;
import br.com.fiap.gs.model.SolucaoEspacial;
import br.com.fiap.gs.model.StatusSolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolucaoRepository extends JpaRepository<SolucaoEspacial, Long> {

    // Buscar por área de impacto
    List<SolucaoEspacial> findByAreaImpacto(AreaImpacto areaImpacto);

    // Buscar por ODS
    List<SolucaoEspacial> findByOds(ODS ods);

    // Buscar por status
    List<SolucaoEspacial> findByStatus(StatusSolucao status);

    // Soluções com prioridade alta (>= 12)
    @Query("SELECT s FROM SolucaoEspacial s WHERE s.prioridade >= 12 ORDER BY s.prioridade DESC")
    List<SolucaoEspacial> findSolucoesPrioridadeAlta();

    // Contagem por status
    @Query("SELECT s.status, COUNT(s) FROM SolucaoEspacial s GROUP BY s.status")
    List<Object[]> countByStatus();

    // Contagem por área de impacto
    @Query("SELECT s.areaImpacto, COUNT(s) FROM SolucaoEspacial s GROUP BY s.areaImpacto")
    List<Object[]> countByAreaImpacto();
}
