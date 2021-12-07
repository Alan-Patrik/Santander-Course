package com.alanpatrik.exercise1.entities;

import java.util.Scanner;

public class Multiplication extends Calculator {

    double num1 = 0;
    double num2 = 0;
    double total = 0;

    Scanner scan = new Scanner(System.in);

    public void multiplication() {
        System.out.print("| Enter the first number: ");
        num1 = scan.nextDouble();
        System.out.print("| Enter the second number: ");
        num2 = scan.nextDouble();

        this.total = super.multiplication(num1, num2);
        System.out.println("\n" + displayResult());
    }

    public String displayResult() {
        return num1 + " x " +
                num2 +
                " = " + total;
    }
}
