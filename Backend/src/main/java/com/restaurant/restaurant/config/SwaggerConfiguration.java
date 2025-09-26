package com.restaurant.restaurant.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Resturant Endpoints",
                description = "all apis for Resturant",
                contact = @Contact(
                        name = "Ibrahim Abouzaid",
                        email = "Ibrahim.m.abouzaid@gmail.com",
                        url = "https://www.linkedin.com/in/ibrahim_abouzaid"
                ),
                license = @License(
                        name = "resturant license",
                        url = "http://localhost:4200"
                ),
                version = "1"
        )
)
@Configuration
public class SwaggerConfiguration {
}
