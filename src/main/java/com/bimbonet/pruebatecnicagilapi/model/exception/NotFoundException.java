package com.bimbonet.pruebatecnicagilapi.model.exception;

/**
 * Excepción lanzada cuando un recurso solicitado no se encuentra. Puede ser
 * debido a la búsqueda de un ID inexistente o un recurso no disponible.
 *
 * @author Gilberto García
 * @version 1.0
 * @since 2023-09-03
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Recurso no encontrado.");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
