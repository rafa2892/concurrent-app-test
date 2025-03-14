package com.ariadna.eventmanager.services;

import com.ariadna.eventmanager.models.Evento;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoService {

    // Método para agregar eventos
    void agregarEvento(Evento evento);

    // Buscar eventos por nombre de fuente
    List<Evento> buscarPorNombreDeFuente(String nombreFuente);

    List<Evento> buscarEntreFechas(String fechaInicio, String fechaFin) throws Exception;

    // Buscar eventos por rango de valores
    List<Evento> buscarPorRangoDeValores(double valorMin, double valorMax);

    int totalEventosRegistrados();
}
