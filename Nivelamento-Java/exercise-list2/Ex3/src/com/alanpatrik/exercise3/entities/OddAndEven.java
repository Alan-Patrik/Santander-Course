package com.alanpatrik.exercise3.entities;

public class OddAndEven {

    public void oddAndEven(int[] array) {
        int count = 0;
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i] % 2 != 0) {
                System.out.println(array[i]);
            }
        }

        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i] % 2 == 0) {
                System.out.println(array[i]);
            }
        }
    }
}
