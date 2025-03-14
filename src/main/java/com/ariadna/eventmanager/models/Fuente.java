package com.ariadna.eventmanager.models;

public class Fuente {

    private long id;
    private String nombre;

    // Constructor
    public Fuente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
