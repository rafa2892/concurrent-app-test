/*
    Clase controlador encargada de procesar y recibir las peticiones
 */


package com.ariadna.eventmanager.controllers;

import com.ariadna.eventmanager.exceptions.FechasInconsistentesException;
import com.ariadna.eventmanager.exceptions.ValoresInvalidosException;
import com.ariadna.eventmanager.bootstrap.FileLoader;
import com.ariadna.eventmanager.models.Evento;
import com.ariadna.eventmanager.services.EventoService;
import com.ariadna.eventmanager.services.FuenteService;
import com.ariadna.eventmanager.utils.Constantes;
import com.ariadna.eventmanager.utils.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    @Qualifier("eventoService")
    private EventoService eventoService;

    @Autowired
    @Qualifier("fuenteService")
    private FuenteService fuenteService;

    private static final Logger logger = LogManager.getLogger(FileLoader.class);


    //Req 1 Buscar eventos que contengan un nombre de fuente.
    @GetMapping("/buscarEventosPorFuente")
    public ResponseEntity<ResponseMessage<List<Evento>>> buscarEventosPorFuente(@RequestParam String nombreFuente) {

        List<Evento> eventos = eventoService.buscarPorNombreDeFuente(nombreFuente);

        String mensaje = eventos.isEmpty() ? Constantes.NO_ELEMENTO_ENCONTRADO + nombreFuente  + "." :
            Constantes
                .crearMensajeBusquedaPorFuentes
                     (nombreFuente,eventos.size(),fuenteService.totalFuentesRegistradas(), eventoService.totalEventosRegistrados());

        ResponseMessage<List<Evento>> response = new ResponseMessage<>(mensaje, eventos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/buscarEntreFechas")
    public ResponseEntity<ResponseMessage<List<Evento>>> buscarEntreFechas(@RequestParam String fechaInicio, @RequestParam String fechaFin) {

        List<Evento> eventos = null;

        try {
            eventos = eventoService.buscarEntreFechas(fechaInicio, fechaFin);
        } catch (DateTimeParseException e) {
            ResponseMessage<List<Evento>> response = new ResponseMessage<>(Constantes.ERROR_FORMATO_FECHA, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch(FechasInconsistentesException i) {
            ResponseMessage<List<Evento>> response = new ResponseMessage<>(Constantes.ERROR_FECHAS_INCONSISTENTES, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch(Exception e) {
            ResponseMessage<List<Evento>> response = new ResponseMessage<>(e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Crear la respuesta
        ResponseMessage<List<Evento>> response =
                            new ResponseMessage<>(Constantes.
                                    crearMensajeBusquedaPorFecha(fechaInicio,fechaFin,eventos.size(),
                                            fuenteService.totalFuentesRegistradas(), eventoService.totalEventosRegistrados()), eventos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Buscar eventos dentro de un rango de valores (valor min., valor máx.)
    @GetMapping("/buscarPorRangoDeValores")
    public ResponseEntity<ResponseMessage<List<Evento>>> buscarPorRangoDeValores(@RequestParam double valorMin, @RequestParam double valorMax) {

        List<Evento> eventos = null;

        try {
            eventos = eventoService.buscarPorRangoDeValores(valorMin, valorMax);
        }
        catch (ValoresInvalidosException e) {
            ResponseMessage<List<Evento>> response = new ResponseMessage<>(e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        // Crear la respuesta
        ResponseMessage<List<Evento>> response =
                new ResponseMessage<>(Constantes.
                        crearMensajeBusquedaPorRangoValores(valorMin,valorMax,eventos.size(),
                                fuenteService.totalFuentesRegistradas()
                                    ,eventoService.totalEventosRegistrados()),eventos);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

