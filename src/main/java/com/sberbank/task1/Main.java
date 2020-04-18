package com.sberbank.task1;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int [] massive = new int[10];
        Random random = new Random();

        for (int i = 0; i < massive.length; i++){
            massive[i] = random.nextInt(10);
        }

        for (int i = 0; i < massive.length; i++){
            System.out.print(massive[i] + " ");
        }

        int term = 0;

        for (int i = 0; i < massive.length; i++){
            for (int j = 0; j < massive.length - 1; j++){
                if (massive[j] > massive [j+1]) {
                    term = massive[j];
                    massive[j] = massive[j + 1];
                    massive[j + 1] = term;
                }
            }
        }

        System.out.println(" ");

        for (int i = 0; i < massive.length; i++){
            System.out.print(massive[i] + " ");
        }
    }
}
