package ru.petrojectgroup.conscience.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Conscience Api",
                description = "Pet-project group",
                version = "1.0.0",
                contact = @Contact(
                        name = "Klimova Viktoriya",
                        email = "klimof_v@mail.ru",
                        url = "https://t.me/Viktoriya_Klim")
        )
)
public class OpenApiConfig {
}
