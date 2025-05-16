package com.example.order_service;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");
        
        Contact contact = new Contact();
        contact.setName("Your Name");
        contact.setEmail("your.email@example.com");
        
        Info information = new Info()
            .title("Product API")
            .version("1.0.0")
            .description("API for managing products")
            .contact(contact);
        
            
        return new OpenAPI().info(information).servers(List.of(server));
    }
}