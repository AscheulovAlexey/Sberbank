package com.sberbank.task1;

import java.util.Arrays;
import java.util.Random;

public class RandomService {

    public static int[] generateRandomMassive(int[] massive){

        Random random = new Random();
        for (int i = 0; i < massive.length; i++)
            massive[i] = random.nextInt(10);
        System.out.println("Сгенерированный массив: \n" + Arrays.toString(massive));

        return massive;
    }

}
