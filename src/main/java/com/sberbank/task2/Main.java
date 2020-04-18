package com.sberbank.task2;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String inputString = "Сапог саРай арбуз болт БоКс биржа лалала лол мапа кто арара бум серый";
        System.out.println("Входная строка:\n" + inputString);

        SortStringService sortStringService = new SortStringService();
        Map<String, List<String>> dictionary = sortStringService.transformStringToSortedMap(inputString);
        System.out.println("\nОтсортированная строка:\n" + dictionary);
    }

}
