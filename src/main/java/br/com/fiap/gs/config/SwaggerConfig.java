package br.com.fiap.gs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GS - API de Soluções da Economia Espacial")
                        .description("""
                                API REST para cadastro, gestão e acompanhamento de soluções tecnológicas
                                ligadas à economia espacial.
                                
                                **Tema escolhido:** Monitoramento de Desmatamento por Satélite
                                
                                **ODS relacionado:** ODS 15 - Vida Terrestre
                                
                                **Regras de negócio implementadas:**
                                - Prioridade calculada automaticamente: urgência × impacto
                                - Não é possível alterar soluções CANCELADAS
                                - Não é possível excluir soluções VALIDADAS
                                - Retorno 404 quando solução não for encontrada
                                - Retorno 400 para dados inválidos
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Grupo GS - 2ESPR")
                                .email("grupo@fiap.com.br"))
                        .license(new License()
                                .name("FIAP - 2025")));
    }
}
