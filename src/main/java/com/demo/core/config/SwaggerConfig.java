package com.demo.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {

        String title = "ADDRESS BOOK";
        String description = "";
        String version = "1.0";
        String termsOfServiceUrl = "";
        Contact contact = new Contact("contact name", "https://spring.io/", "contact@example.com.tr");
        String license = "lorem ipsum dolor sit amet";
        String licenseUrl = "";

        return new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl);

    }
}
