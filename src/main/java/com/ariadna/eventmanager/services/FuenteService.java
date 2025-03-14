package com.ariadna.eventmanager.services;

import com.ariadna.eventmanager.models.Fuente;

public interface FuenteService {
    Fuente getFuenteById(long id);

    void addFuente(Fuente fuente);

    int totalFuentesRegistradas();
}
