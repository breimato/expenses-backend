package com.expenses.config;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The Class Jackson Config. */
@Configuration
public class JacksonConfig {

    /**
     * Json nullable module customizer.
     *
     * @return the jackson 2 object mapper builder customizer
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonNullableModuleCustomizer() {

        return builder -> builder.modulesToInstall(new JsonNullableModule());
    }
}
