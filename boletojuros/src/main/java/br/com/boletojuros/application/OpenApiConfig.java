package br.com.boletojuros.application;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Boleto Juros API")
                .description("API para cálculo de boletos com juros")
                .version("v1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
