package com.alanpatrik.exercise2.entities;

import java.util.Scanner;

public class MultiplicationTable {
    private int numberForMultiplication = 0;

    public void multiplicationTable(int number) {
        for (int i = 1; i <= number; i++) {
            this.numberForMultiplication = i * number;
            System.out.println(i + " x " + number + " = " + this.numberForMultiplication);
        }
    }
}
