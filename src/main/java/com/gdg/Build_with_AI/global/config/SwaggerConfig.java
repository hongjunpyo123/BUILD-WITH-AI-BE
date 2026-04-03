package com.gdg.Build_with_AI.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("API")
            .version("1.0.0"))
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .servers(List.of(
            new Server().url("https://36c3-203-237-81-98.ngrok-free.app")
        ));
  }
}
