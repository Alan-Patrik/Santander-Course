package com.alanpatrik.exercise1.entities;

import java.util.Scanner;

public class Subtraction extends Calculator {

    double num1 = 0;
    double num2 = 0;
    double total = 0;

    Scanner scan = new Scanner(System.in);

    public void subtraction() {
        System.out.print("| Enter the first number: ");
        num1 = scan.nextDouble();
        System.out.print("| Enter the second number: ");
        num2 = scan.nextDouble();

        this.total = super.subtraction(num1, num2);
        System.out.println("\n" + displayResult());
    }

    public String displayResult() {
        return num1 + " - " +
                num2 +
                " = " + total;
    }
}
