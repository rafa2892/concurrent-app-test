package com.ariadna.eventmanager.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class GenerateEventData {

    public static void main(String[] args) {

        LocalDateTime start = LocalDateTime.of(2024, 8, 1, 23, 59);
        LocalDateTime end = LocalDateTime.of(2024, 8, 12, 23, 59);
        Random random = new Random();

        for (int fileIndex = 1; fileIndex <= 8; fileIndex++) {
            try (FileWriter writer = new FileWriter("eventos" + fileIndex + ".txt")) {
                for (int i = (fileIndex - 1) * 167 + 1; i <= fileIndex * 167; i++) {
                    int fuenteId = random.nextInt(200) + 1;
                    LocalDateTime randomDate = start.plus(random.nextLong(ChronoUnit.SECONDS.between(start, end)), ChronoUnit.SECONDS);
                    double valor = 10 + (990 * random.nextDouble());
                    writer.write(String.format("%d,%d,%s,%.2f%n", i, fuenteId, randomDate.toString(), valor));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
