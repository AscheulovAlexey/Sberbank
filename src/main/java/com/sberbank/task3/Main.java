package com.sberbank.task3;

import com.revinate.guava.util.concurrent.RateLimiter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
Консольная утилита для скачивания файлов по HTTP
Принимает: список ссылок в текстовом файле
Действие: скачивает эти файлы и кладет в указанную папку на локальном диске.
Должен уметь качать несколько файлов одновременно (в несколько потоков, например, 3 потока)
и выдерживать указанное ограничение на скорость загрузки, например, 500 килобайт в секунду.
*/

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            throw new IllegalArgumentException("Необходимо добавить 2 аргумента в метод Main: " +
                    "1 - путь к файлу со ссылками, 2 - путь к папке, где будут сохраняться файлы");
        }

        System.out.println("Требуемое количество потоков (1 поток - 1 файл) для скачивания. Например, 1,2,3...n\n" +
                "Введите число: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfThreads = Integer.parseInt(reader.readLine());

        System.out.println("\nТребуемое ограничение скорости скачивания. Например, 512 = 512кб/с.\n" +
                "Введите число: ");
        int limitSpeed = Integer.parseInt(reader.readLine());
        GlobalVariables.globalRateLimiter = RateLimiter.create(limitSpeed * 1024);

        System.out.println("\nСкачивание началось...\n");

        List<String> links = ReadTextFileService.readTextFile(args[0]);

        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        links.forEach(link -> executor.submit(new DownloadService(link, args[1])));
        executor.shutdown();

        while (!executor.isTerminated()) {
            try {
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AssertionError(e);
            }
        }
        long endTime = System.nanoTime();

        SpeedService.measureSpeedDownloadAllThread(startTime, endTime);

    }
}
