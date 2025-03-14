package com.ariadna.eventmanager.utils;

public class ResponseMessage<T> {
    private String mensaje;
    private T datos;

    public ResponseMessage(String mensaje, T datos) {
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }
}
