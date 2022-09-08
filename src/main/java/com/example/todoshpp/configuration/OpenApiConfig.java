package com.example.bookjpa.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * this class configure swagger view where
 * add pom version
 */
@Configuration
public class OpenApiConfig {
    private final Logger log = LoggerFactory.getLogger(OpenApiConfig.class);
    private final BuildProperties buildProperties;

    public OpenApiConfig(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    OpenAPI customOpenAPI() {
        log.info("customOpenAPI has created");
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(buildProperties.getArtifact() + " - API by mentor SHPP")
                        .version(buildProperties.getVersion())
                        .description(buildProperties.getArtifact() + " - API Swagger documentation")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
