package pl.lechowicz.queansserver.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private final String version = "0.1";

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Questions Answers API")
                .description("API for prepare interview questions and answers")
                .version(this.version)
                .contact(apiContact());
    }

    private Contact apiContact() {
        return new Contact()
                .name("Michał Lechowicz")
                .url("https://github.com/ichal6");
    }

}
