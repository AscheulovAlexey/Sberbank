package com.sberbank.task1;

import java.util.Arrays;
import java.util.Random;

/*
Напишите программу для сортировки массива, использующую метод пузырька
*/

public class Main {

    public static void main(String[] args) {

        int[] massive = new int[10];
        RandomService.generateRandomMassive(massive);
        SortService.sortMassiveUsingBubbleSort(massive);
        System.out.println("\nОтсортированный массив: \n" + Arrays.toString(massive));
    }
}
