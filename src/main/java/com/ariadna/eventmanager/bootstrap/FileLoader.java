package com.ariadna.eventmanager.bootstrap;

import com.ariadna.eventmanager.models.Evento;
import com.ariadna.eventmanager.models.Fuente;
import com.ariadna.eventmanager.services.EventoService;
import com.ariadna.eventmanager.services.FuenteService;
import com.ariadna.eventmanager.utils.Constantes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.List;
import java.util.ArrayList;

@Component
public class FileLoader {

    @Autowired
    private  FuenteService fuenteService;
    @Autowired
    private  EventoService eventoService;

    private  ExecutorService executorService;

    private static final Logger logger = LogManager.getLogger(FileLoader.class);

    //Crea varios hilos para agilizar el proceso de carga
    public FileLoader() {
        this.executorService = Executors.newFixedThreadPool(4); // Configura el pool con 4 hilos
    }

    public void loadFuentesFromFiles(List<String> fileNames) throws IOException {
        List<Future<Void>> futures = new ArrayList<>();

        for (String fileName : fileNames) {
            fileName = Constantes.DIRECTORIO_DATA + fileName;
            futures.add(executorService.submit(new LoadFuenteFileTask(fileName)));
        }

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class LoadFuenteFileTask implements Callable<Void> {
        private final String fileName;
        public LoadFuenteFileTask(String fileName) {
            this.fileName = fileName;
        }
        @Override
        public Void call() {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                if (inputStream == null) {
                    logger.error("El archivo no se encontró en el classpath: data/" + fileName);
                    return null;
                }

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    int fuenteId = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    Fuente fuente = new Fuente(fuenteId, nombre);
                    fuenteService.addFuente(fuente);
                }
                logger.info("Se han cargado correctamente las fuentes del fichero: " + fileName);
            } catch (IOException e) {
                logger.error("Error leyendo el fichero: " + fileName, e);
            }
            return null;
        }
    }

    public void loadEventosFromFiles(List<String> fileNames) {
        List<Future<Void>> futures = new ArrayList<>();

        for (String fileName : fileNames) {
            fileName = Constantes.DIRECTORIO_DATA + fileName;
            futures.add(executorService.submit(new LoadFileTask(fileName)));
        }

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                logger.error(Constantes.ERROR_CARGA, e);
            }
        }
        executorService.shutdown();
    }

    private class LoadFileTask implements Callable<Void> {
        private final String fileName;
        public LoadFileTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public Void call() {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    int eventoId = Integer.parseInt(parts[0]);
                    int fuenteId = Integer.parseInt(parts[1]);
                    LocalDateTime timestamp = LocalDateTime.parse(parts[2]);
                    double valor = Double.parseDouble(parts[3]);
                    Fuente fuente = fuenteService.getFuenteById(fuenteId);
                    Evento evento = new Evento(eventoId, fuente, timestamp, valor);
                    eventoService.agregarEvento(evento);
                }
                logger.info("Se ha cargado correctamente los datos de " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
