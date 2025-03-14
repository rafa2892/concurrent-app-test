package com.ariadna.eventmanager.models;

import java.time.LocalDateTime;

public class Evento {

    private int id;

    //Cada evento contendra un objeto fuente
    private Fuente fuente;
    private LocalDateTime timestamp;
    private double valor;

    // Constructor
    public Evento(int id, Fuente fuente, LocalDateTime timestamp, double valor) {
        this.id = id;
        this.fuente = fuente;
        this.timestamp = timestamp;
        this.valor = valor;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fuente getFuente() {
        return fuente;
    }

    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
