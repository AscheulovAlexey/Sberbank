package com.sberbank.task3;

public class SpeedService {

    public static void measureSpeedDownloadOneThread(long startTime, long endTime, int fileSize, String threadName){

        double downloadTimeSeconds = ((double) (endTime - startTime)) / 1000000000d;
        int kBytesPerSecond = (int) ((fileSize / downloadTimeSeconds) / 1024);

        System.out.println("\nЗавершена загрузка файла из потока: " +  threadName + "\n" +
                "Размер файла: " + GlobalVariables.totalSize / 1024 + " kb\n" +
                "Время скачивания: " + downloadTimeSeconds + "\n" +
                "Скорость скачивания: " + kBytesPerSecond + " kb/s\n");
    }

    public static void measureSpeedDownloadAllThread(long startTime, long endTime){

        double downloadTimeSeconds = ((double) (endTime - startTime)) / 1000000000d;
        int kBytesPerSecond = (int) ((GlobalVariables.totalSize / downloadTimeSeconds) / 1024);

        System.out.println("-------------------\n" +
                "Завершена загрузка всех файлов!\n" +
                "Размер всех файлов: " + GlobalVariables.totalSize / 1024 + " kb\n" +
                "Общее время скачивания: " + downloadTimeSeconds + "\n" +
                "Скорость скачивания для всех потоков: " + kBytesPerSecond + " kb/s\n");
    }

}
