package br.com.fiap.carbontrace.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI carbonTraceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CarbonTrace API")
                        .description("API REST para monitoramento ambiental com dados satelitais, análise de desmatamento, emissão de alertas e notificação de órgãos ambientais.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe CarbonTrace")
                                .email("contato@carbontrace.com"))
                        .license(new License()
                                .name("FIAP Global Solution 2026/1")));
    }
}