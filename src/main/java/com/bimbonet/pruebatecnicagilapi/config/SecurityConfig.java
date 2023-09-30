package com.bimbonet.pruebatecnicagilapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuración de seguridad de la aplicación.
 * <p>
 * Define las reglas de autenticación y autorización para los diferentes
 * endpoints, y otras configuraciones relacionadas con la seguridad, como CSRF.
 * </p>
 *
 * @author Gilberto García
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("Configurando la seguridad...");
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                // Otyras Configurations
                .and()
                .headers().frameOptions().disable(); // Habilitar console H2
    }
}
