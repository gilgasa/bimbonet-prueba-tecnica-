package com.bimbonet.pruebatecnicagilapi.config;

import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuración de Swagger para documentación de la API.
 * <p>
 * Swagger se utiliza para generar documentación interactiva para la API. Esta
 * configuración define los detalles básicos de la API, como título, descripción
 * y términos de servicio. También se puede encontrar información sobre el
 * contacto y la licencia.
 * </p>
 *
 * @author Gilberto García
 */
@Configuration
public class SwaggerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfig.class);

    @Value("${version}")
    private String appVersion;

    @Value("${user.role}")
    private String userRole;

    @Bean
    public Docket api() {
        LOGGER.info("Configurando Swagger con la versión: {}", appVersion);
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bimbonet.pruebatecnicagilapi.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfoBuilder()
                .title("Prueba Técnica Bimbonet API")
                .description("Esta API simula el funcionamiento de las máquinas expendedoras de productos para BimboNet. "
                        + "La aplicación sigue una arquitectura en capas y utiliza patrones de diseño como Singleton, Repository Pattern, y más.")
                .termsOfServiceUrl("URL-terminos-de-servicio")
                .contact(new Contact("Gilberto García", "https://github.com/gilgasa", "gilgasan1@gmail.com"))
                .license("Licencia Prueba Tecnica 1.0")
                .version(appVersion)
                .build();
    }

}
