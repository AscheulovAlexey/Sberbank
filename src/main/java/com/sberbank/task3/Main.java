package com.sberbank.task3;

import com.revinate.guava.util.concurrent.RateLimiter;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//Консольная утилита для скачивания файлов по HTTP
//Принимает: список ссылок в текстовом файле
//Действие: скачивает эти файлы и кладет в указанную папку на локальном диске.
//Должен уметь качать несколько файлов одновременно (в несколько потоков, например, 3 потока)
//и выдерживать указанное ограничение на скорость загрузки, например, 500 килобайт в секунду.

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length != 2) {
            throw new IllegalArgumentException("Необходимо добавить 2 аргумента в метод Main: " +
                    "1 - путь к файлу со ссылками, 2 - путь к папке, где будут сохраняться файлы");
        }

        System.out.println("Требуемое количество потоков (1 поток - 1 файл) для скачивания. Например, 1,2,3...n\n" +
                "Введите число: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfThreads = scanner.nextInt();

        System.out.println("\nТребуемое ограничение скорости скачивания. Например, 512 = 512кб/с.\n" +
                "Введите число: ");
        GlobalVariables.globalRateLimiter = RateLimiter.create(scanner.nextInt() * 1024);

        System.out.println("\nСкачивание началось...\n");

        ReadTextFileService readTextFileService = new ReadTextFileService();
        List<String> links = readTextFileService.readTextFile(args[0]);

        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        links.forEach(link -> executor.submit(new DownloadService(link, args[1])));
        executor.shutdown();

        while (executor.isTerminated() == false) {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        }
        long endTime = System.nanoTime();

        SpeedService.measureSpeedDownloadAllThread(startTime, endTime);

    }
}
