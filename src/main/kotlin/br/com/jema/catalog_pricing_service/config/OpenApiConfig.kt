package br.com.jema.catalog_pricing_service.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customApi(): OpenAPI {
        return OpenAPI().info(
            Info()
                .title("Catalog Pricing Service API")
                .version("1.0.0")
                .description("API documentation for the Catalog Pricing Service")
        )
    }
}