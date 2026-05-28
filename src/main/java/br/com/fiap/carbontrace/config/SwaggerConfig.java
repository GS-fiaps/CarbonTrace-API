package br.com.fiap.carbontrace.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI carbonTraceOpenAPI() {
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Ambiente Local");

        return new OpenAPI()
                .servers(List.of(localServer))
                .info(new Info()
                        .title("CarbonTrace API")
                        .description("""
                                API REST desenvolvida para a Global Solution FIAP 2026/1.

                                A solução CarbonTrace tem como objetivo monitorar regiões ambientais por meio de dados satelitais,
                                registrar ocorrências ambientais, realizar análises de desmatamento, emitir alertas e notificar
                                órgãos ambientais responsáveis.

                                Principais recursos da API:
                                - Cadastro de usuários
                                - Cadastro de estados e regiões monitoradas
                                - Cadastro de satélites e imagens satelitais
                                - Registro de ocorrências ambientais
                                - Análises de áreas desmatadas
                                - Emissão de alertas
                                - Notificação de órgãos ambientais
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe CarbonTrace")
                                .email("contato@carbontrace.com")
                                .url("https://github.com/GS-fiaps/"))
                        .license(new License()
                                .name("FIAP Global Solution 2026/1")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório do projeto no GitHub")
                        .url("https://github.com/GS-fiaps/CarbonTrace-API"));
    }
}