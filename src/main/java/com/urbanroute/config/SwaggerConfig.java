package com.urbanroute.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
       info = @Info(title = "Urban Route",
            summary = "Community-driven urban transit routing API with multi-mode pathfinding",
            contact = @Contact(
                    name = "Ashutosh",
                    url = "https://github.com/Ashutosh875/urbanRoute",
                    email = "ashutoshjha6464@gmail.com"
                ),
            version = "1.0",
            description = "A REST API where users contribute local travel routes (bus, metro, auto) between stops. Implements BFS for shortest path and Dijkstra for fastest and cheapest routes, with a Strategy Pattern switching algorithms at runtime. Features JWT authentication, role-based access control, and an in-memory graph cache backed by PostgreSQL"
        ),
        security = {
               @SecurityRequirement(name = "bearerAuth")
       }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
