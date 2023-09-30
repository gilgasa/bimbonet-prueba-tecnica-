package com.bimbonet.pruebatecnicagilapi.model.exception;

/**
 * Excepción lanzada cuando la solicitud hecha por el cliente es malformada o inválida.
 * Esto puede ser debido a parámetros inválidos, datos requeridos faltantes u otras formas
 * de malas solicitudes por parte del cliente.
 *
 * @author Gilberto García
 * @version 1.0
 * @since 2023-09-03
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
