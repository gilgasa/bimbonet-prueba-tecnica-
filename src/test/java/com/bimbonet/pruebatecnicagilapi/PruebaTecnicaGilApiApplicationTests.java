package com.bimbonet.pruebatecnicagilapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Integration test class for the PruebaTecnicaGilApiApplication. This class
 * tests the application's ability to start its context successfully. While the
 * test may seem simplistic, it is critical for ensuring that the basic wiring
 * of the application (like bean definitions and other configurations) is set up
 * correctly.
 *
 * @author Gilberto García
 * @version 1.0
 * @since 2023-09-03
 */
@SpringBootTest
class PruebaTecnicaGilApiApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(PruebaTecnicaGilApiApplicationTests.class);

    /**
     * Tests the application context loading. This test checks that the Spring
     * application starts its context without any issues.
     */
    @Test
    void contextLoads() {
        LOGGER.info("Probando la carga de contexto...");
    }
}
