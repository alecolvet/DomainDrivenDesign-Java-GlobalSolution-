package br.com.fiap.gs.config;

import br.com.fiap.gs.model.*;
import br.com.fiap.gs.repository.SolucaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(SolucaoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                // Solução 1 - Alta prioridade
                SolucaoEspacial s1 = new SolucaoEspacial(
                        "SentinelaVerde - Monitoramento Amazônico",
                        "Sistema de monitoramento em tempo real de desmatamento na Amazônia Legal " +
                        "utilizando imagens de satélite Sentinel-2 e algoritmos de IA para detecção " +
                        "de corte raso e degradação florestal.",
                        AreaImpacto.MEIO_AMBIENTE,
                        ODS.ODS_15_VIDA_TERRESTRE,
                        5, 5
                );
                repository.save(s1);

                // Solução 2 - Em desenvolvimento
                SolucaoEspacial s2 = new SolucaoEspacial(
                        "AgroSat - Irrigação Inteligente",
                        "Plataforma que utiliza dados de satélite para otimizar a irrigação em " +
                        "lavouras do cerrado, reduzindo desperdício de água em até 40% através de " +
                        "mapas de umidade do solo por sensoriamento remoto.",
                        AreaImpacto.AGRONEGOCIO,
                        ODS.ODS_2_FOME_ZERO,
                        4, 4
                );
                s2.setStatus(StatusSolucao.EM_DESENVOLVIMENTO);
                repository.save(s2);

                // Solução 3 - Validada
                SolucaoEspacial s3 = new SolucaoEspacial(
                        "AlertaRisco - Prevenção de Deslizamentos",
                        "Sistema de alerta precoce para deslizamentos de terra em encostas urbanas, " +
                        "combinando dados de radar SAR com modelos hidrológicos e alertas automáticos " +
                        "para defesa civil.",
                        AreaImpacto.GESTAO_DE_RISCOS,
                        ODS.ODS_11_CIDADES_SUSTENTAVEIS,
                        5, 4
                );
                s3.setStatus(StatusSolucao.VALIDADA);
                repository.save(s3);

                // Solução 4 - Ativa
                SolucaoEspacial s4 = new SolucaoEspacial(
                        "ConectaRemoto - Internet via Satélite Rural",
                        "Solução de conectividade de baixo custo para comunidades rurais e ribeirinhas " +
                        "da região Norte, utilizando satélites LEO para fornecer acesso à educação " +
                        "e telemedicina.",
                        AreaImpacto.CONECTIVIDADE,
                        ODS.ODS_4_EDUCACAO_DE_QUALIDADE,
                        3, 4
                );
                s4.setStatus(StatusSolucao.ATIVA);
                repository.save(s4);

                // Solução 5 - Proposta, baixa prioridade
                SolucaoEspacial s5 = new SolucaoEspacial(
                        "MineMap - Mapeamento de Recursos Minerais",
                        "Ferramenta de mapeamento geológico usando imageamento hiperespectral por " +
                        "satélite para identificação de depósitos minerais em regiões de difícil acesso.",
                        AreaImpacto.MINERACAO,
                        ODS.ODS_9_INDUSTRIA_E_INOVACAO,
                        2, 3
                );
                repository.save(s5);

                System.out.println("✅ Banco de dados populado com 5 soluções espaciais de exemplo.");
            }
        };
    }
}
