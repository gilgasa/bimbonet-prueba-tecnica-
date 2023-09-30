package com.bimbonet.pruebatecnicagilapi.model.exception;

/**
 * Excepción lanzada cuando ocurre un error interno inesperado. Típicamente,
 * representa errores que no deberían ocurrir bajo operaciones normales.
 *
 * @author Gilberto García
 * @version 1.0
 * @since 2023-09-03
 */
public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message) {
        super(message);
    }
}
