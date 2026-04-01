package com.Homework.AnimalShelter.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Animal Shelter Management API")
                        .version("1.0.0")
                        .description("REST API для системы управления приютом животных. " +
                                "Предоставляет функционал для работы с пользователями, приютами, " +
                                "кандидатами на усыновление, напоминаниями, отчётами и логами взаимодействий.")
                        .contact(new Contact()
                                .name("Техническая поддержка")
                                .url("http://localhost:8080")
                                .email("support@animalshelter.homework.com"))
                        .license(new License()
                                .name("Proprietary License")
                                .url("http://animalshelter.homework.com/license")))
                .components(new Components());
    }
}
