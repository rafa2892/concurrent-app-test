/*
    Clase de Excepciones para fechas con el formato invalido con mensaje personalizado.
 */

package com.ariadna.eventmanager.exceptions;

public class FechaInvalidaException extends RuntimeException {
    public FechaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
