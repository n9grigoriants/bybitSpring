package ru.varino.bybit.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "bybit",
                version = "0.0.1-SNAPSHOT",
                description = "bybit",
                contact = @Contact(
                        name = "Support",
                        email = "support@bybit.example.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = @Server(
                url = "/api",
                description = "Основной сервер"
        )
)
public class OpenApiConfig { }