package com.ariadna.eventmanager.utils;

import java.time.format.DateTimeFormatter;

public class Constantes {

    public final static String DIRECTORIO_DATA = "data/";
    public final static String ERROR_FORMATO_FECHA = "Formato de fecha inválido. Usa el formato ISO 8601: yyyy-MM-dd'T'HH:mm:ss";
    public final static String ERROR_FECHAS_INCONSISTENTES = "La fecha de inicio no puede ser posterior a la fecha de fin.";
    public final static String ERROR_VALORES_INVALIDOS = "El valor mínimo no puede ser mayor que el valor máximo.";
    public final static String NO_ELEMENTO_ENCONTRADO = "No se encontraron eventos para la fuente : ";
    public final static String ERROR_CARGA = "Error al cargar los eventos desde el archivo: ";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final String ERROR_INICIO = "Error al arrancar la aplicación";

    public static String crearMensajeBusquedaPorFuentes(String nombreFuente, int elementos, int numeroFuentes, int numeroEventos) {

        // Crear y retornar el mensaje
        return String.format(
                "Se han encontrado %d eventos de la fuente: %s." +
                        " Número total de elementos registrados: %d (incluye %d fuentes y %d eventos).",
                elementos, nombreFuente, (numeroFuentes + numeroEventos), numeroFuentes, numeroEventos
        );

    }

    public static String crearMensajeBusquedaPorFecha(String fechaInicio, String fechaFin, int elementos,int numeroFuentes, int numeroEventos) {

        // Crear y retornar el mensaje con el formato de fecha legible
        return String.format(
                "Se ha encontrado %d eventos encontrados entre las fechas %s y %s." +
                        "Número total de elementos: %d (incluye %d fuentes y %d eventos).",
                elementos, fechaInicio, fechaFin,
                (numeroFuentes + numeroEventos), numeroFuentes, numeroEventos
        );
    }

    public static String crearMensajeBusquedaPorRangoValores(double valorMin, double valorMax, int elementos, int numeroFuentes, int numeroEventos) {
        // Crear y retornar el mensaje
        return String.format(
                "Se han encontrado %d eventos cuyo valor está en un rango entre: %.2f y %.2f." +
                        "Número total de elementos: %d (incluye %d fuentes y %d eventos).",
                elementos, valorMin, valorMax, (numeroFuentes + numeroEventos), numeroFuentes, numeroEventos
        );
    }
}
