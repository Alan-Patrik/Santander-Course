package com.alanpatrik.exercise4.entities;

public class FindBiggerSmallerAverage {

    public void findBiggerSmallerAverage(int[] array) {
        int bigger = 0;
        int smaller = 0;
        float sum = 0;
        float average;

        for (int i = 0; i <= array.length - 1; i++) {
            sum += array[i];
            if (array[i] > bigger) {
                bigger = array[i];

            } else if (array[i] < bigger) {
                smaller = array[i];

            }
        }

        average = (sum / array.length) ;

        System.out.println("Bigger => " + bigger);
        System.out.println("Smaller => " + smaller);
        System.out.println("Average => " + average);
    }
}
