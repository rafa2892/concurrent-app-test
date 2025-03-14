/*
    Clase encargada de iniciar los datos al arrancar la aplicación
 */

package com.ariadna.eventmanager.bootstrap;

import com.ariadna.eventmanager.utils.Constantes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private  FileLoader fileLoader;

    private static final Logger logger = LogManager.getLogger(DataLoader.class);


    @Override
    public void run(String... args) {

        List<String> fuenteFiles = Arrays.asList(
                "fuentes1.txt",
                "fuentes2.txt");

        List<String> fileNames = Arrays.asList(
                "eventos1.txt",
                "eventos2.txt",
                "eventos3.txt",
                "eventos4.txt",
                "eventos5.txt",
                "eventos6.txt"
        );

        try {
            fileLoader.loadFuentesFromFiles(fuenteFiles);
            fileLoader.loadEventosFromFiles(fileNames);
            logger.info("*************  APLICACION INICIADA CORRECTAMENTE ************* ");
        } catch (Exception e) {
            logger.error(Constantes.ERROR_INICIO);
        }
    }
}
