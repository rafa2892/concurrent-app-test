package com.ariadna.eventmanager.exceptions;
/*
    Clase de Excepciones para fechas inconsistentes
 */


public class FechasInconsistentesException extends RuntimeException{
    public FechasInconsistentesException(String mensaje) {
        super(mensaje);
    }
}
