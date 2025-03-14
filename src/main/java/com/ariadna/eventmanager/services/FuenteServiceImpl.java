package com.ariadna.eventmanager.services;

import com.ariadna.eventmanager.models.Fuente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("fuenteService")
public class FuenteServiceImpl implements FuenteService {

    private final List<Fuente> fuentes = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Fuente getFuenteById(long id) {
        return fuentes.stream()
                .filter(fuente -> fuente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addFuente(Fuente fuente) {
        fuentes.add(fuente);
    }

    @Override
    public int totalFuentesRegistradas() {
        return fuentes.size();
    }

}
