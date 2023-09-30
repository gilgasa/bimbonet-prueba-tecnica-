package com.bimbonet.pruebatecnicagilapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the PruebaTecnicaGilApi application. This class is
 * responsible for bootstrapping the application using Spring Boot.
 *
 * @author Gilberto García
 * @version 1.0
 * @since 2023-09-03
 */
@SpringBootApplication
public class PruebaTecnicaGilApiApplication {

    /**
     * Main method used to launch the Spring Boot application.
     *
     * @param args Command-line arguments passed during the start of the
     * application.
     */
    public static void main(String[] args) {
        SpringApplication.run(PruebaTecnicaGilApiApplication.class, args);
    }
}
