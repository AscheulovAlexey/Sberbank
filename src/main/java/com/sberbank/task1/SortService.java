package com.sberbank.task1;

import java.util.Arrays;

public class SortService {

    public static void sortMassiveUsingBubbleSort(int[] massive){

        System.out.println("\nПроцесс сортировки");
        int term = 0;
        for (int i = 0; i < massive.length; i++){
            for (int j = 0; j < massive.length - 1; j++){
                if (massive[j] > massive [j+1]) {
                    term = massive[j];
                    massive[j] = massive[j + 1];
                    massive[j + 1] = term;
                }
            }
            System.out.println(Arrays.toString(massive));
        }
    }
}
