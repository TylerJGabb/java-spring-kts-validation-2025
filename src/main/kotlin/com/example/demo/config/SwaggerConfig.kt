package com.example.demo.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                    .title("Example API")
                    .description(
                        "A RESTful Spring Boot API for managing Example resources, supporting create, read, update, and delete operations with validation groups and structured DTO mapping."
                    )
                    .version("v1.0.0")
                    .contact(Contact().name("API Support").email("support@example.com"))
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("Project Docs")
                    .url("https://example.com/docs")
            )
}
