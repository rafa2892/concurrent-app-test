/*
    Clase servicio para los eventos
 */

package com.ariadna.eventmanager.services;

import com.ariadna.eventmanager.exceptions.FechasInconsistentesException;
import com.ariadna.eventmanager.exceptions.ValoresInvalidosException;
import com.ariadna.eventmanager.models.Evento;
import com.ariadna.eventmanager.utils.Constantes;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service("eventoService")
public class EventoServiceImpl implements EventoService{

    private final List<Evento> eventos = Collections.synchronizedList(new ArrayList<>());;

    @Override
    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }

    @Override
    public List<Evento> buscarPorNombreDeFuente(String nombreFuente) {
        return eventos.stream()
                .filter(evento -> evento.getFuente().getNombre().equalsIgnoreCase(nombreFuente))
                .collect(Collectors.toList());
    }

    @Override
    public List<Evento> buscarEntreFechas(String fechaInicio, String fechaFin) throws Exception {

        validarFechas(fechaInicio, fechaFin);
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio, Constantes.FORMATTER);
        LocalDateTime fin = LocalDateTime.parse(fechaFin, Constantes.FORMATTER);

        // Filtrar eventos en el rango considerando fecha y hora
        return eventos.stream()
                .filter(evento -> !evento.getTimestamp().isBefore(inicio) && !evento.getTimestamp().isAfter(fin))
                .collect(Collectors.toList());
    }

    private void validarFechas(String fechaInicio, String fechaFin) {

        LocalDate inicio;
        LocalDate fin;

        inicio = LocalDate.parse(fechaInicio, Constantes.FORMATTER);
        fin = LocalDate.parse(fechaFin, Constantes.FORMATTER);

        // Validar que fechaInicio no sea posterior a fechaFin
        if (inicio.isAfter(fin)) {
            throw new FechasInconsistentesException(Constantes.ERROR_FECHAS_INCONSISTENTES);
        }
    }

    @Override
    public List<Evento> buscarPorRangoDeValores(double valorMin, double valorMax) {

        validarDatosEntrada(valorMin,valorMax);
        return  eventos.stream()
                .filter(evento -> evento.getValor() >= valorMin && evento.getValor() <= valorMax)
                .collect(Collectors.toList());
    }

    @Override
    public int totalEventosRegistrados() {
        return eventos.size();
    }
    private void validarDatosEntrada(double valorMin, double valorMax) {
        if(valorMin > valorMax) {
            throw new ValoresInvalidosException(Constantes.ERROR_VALORES_INVALIDOS);
        }
    }
}
