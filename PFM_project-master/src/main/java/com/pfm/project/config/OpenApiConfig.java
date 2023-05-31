package com.pfm.project.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// swagger springdoc
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("PFM 프로젝트")
                .version("v1")
                .description("PFM API 명세서");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}
