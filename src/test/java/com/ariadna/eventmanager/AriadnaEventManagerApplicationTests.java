package com.ariadna.eventmanager;

import com.ariadna.eventmanager.models.Evento;
import com.ariadna.eventmanager.models.Fuente;
import com.ariadna.eventmanager.services.EventoService;
import com.ariadna.eventmanager.services.EventoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AriadnaEventManagerApplicationTests {


    private EventoService eventoService = new EventoServiceImpl();

    @BeforeEach
    void setUp() {
        // Cargar fuentes y eventos desde archivos de prueba si es necesario
    }

    @Test
    public void testBuscarPorNombreDeFuente() {
        // Configurar datos de prueba
        Fuente fuente = new Fuente(1, "Fuente1");
        eventoService.agregarEvento(new Evento(1, fuente, LocalDateTime.now(), 123.45));

        // Ejecutar el método a probar
        List<Evento> eventos = eventoService.buscarPorNombreDeFuente("Fuente1");

        // Verificar resultados
        assertEquals(1, eventos.size());
        assertEquals("Fuente1", eventos.get(0).getFuente().getNombre());
    }

    @Test
    public void testBuscarEntreFechas() throws Exception {


        // Formatted para las cadenas de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String fechaEvento1 = "05-08-2024 10:00:23";
        String fechaEvento2 = "11-08-2024 21:00:23";

        // Convertir las cadenas a LocalDateTime
        LocalDateTime fechaEvento1LocalDate = LocalDateTime.parse(fechaEvento1, formatter);
        LocalDateTime fechaEvento2LocalDate = LocalDateTime.parse(fechaEvento2, formatter);

        // Configurar datos de prueba
        Fuente fuente = new Fuente(1, "Fuente1");
        eventoService.agregarEvento(new Evento(1, fuente, fechaEvento1LocalDate, 123.45));
        eventoService.agregarEvento(new Evento(2, fuente, fechaEvento2LocalDate, 678.90));

        // Ejecutar el método a probar
        List<Evento> eventos = eventoService.buscarEntreFechas(
                fechaEvento1,
                fechaEvento2
        );

        // Verificar resultados
        assertEquals(2, eventos.size());
    }

    @Test
    public void testBuscarPorRangoDeValores() {
        // Configurar datos de prueba
        Fuente fuente = new Fuente(1, "Fuente1");
        eventoService.agregarEvento(new Evento(1, fuente, LocalDateTime.now(), 100.00));
        eventoService.agregarEvento(new Evento(2, fuente, LocalDateTime.now(), 200.00));

        // Ejecutar el método a probar
        List<Evento> eventos = eventoService.buscarPorRangoDeValores(50.00, 150.00);

        // Verificar resultados
        assertEquals(1, eventos.size());
        assertEquals(100.00, eventos.get(0).getValor());
    }
}
