package br.com.zupacademy.felipe.gadelha.mercadolivre.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@Profile(value = "dev")
public class OpenApiConfig {

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Meli API")
						.description("API que simula a aplicação do Mercado Livre")
						.version("v1.0")
						.contact(new Contact().name("Felipe Gadelha Diniz Da Silva")
								.url("https://www.linkedin.com/in/felipe-gadelha-diniz-da-silva-aaaa4a158/")
								.email("felipegadelha90@gmail.com")))
				.externalDocs(new ExternalDocumentation().description("Meli API Github Documentation")
						.url("https://github.com/FelipeGadelha/orange-talents-06-template-ecommerce"));
	}
}
