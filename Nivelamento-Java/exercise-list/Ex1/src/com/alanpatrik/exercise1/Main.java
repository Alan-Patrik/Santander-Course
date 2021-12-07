package com.alanpatrik.exercise1;

import com.alanpatrik.exercise1.entities.Division;
import com.alanpatrik.exercise1.entities.Multiplication;
import com.alanpatrik.exercise1.entities.Subtraction;
import com.alanpatrik.exercise1.entities.Sum;
import com.alanpatrik.exercise1.layout.Layout;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Layout layout = new Layout();
        Sum sum = new Sum();
        Subtraction subtraction = new Subtraction();
        Multiplication multiplication = new Multiplication();
        Division division = new Division();
        int operation;

        layout.mainMenu();
        operation = scan.nextInt();

        switch (operation) {
            case 1:
                sum.sum();
                layout.finalMenu();
                break;

            case 2:
                subtraction.subtraction();
                layout.finalMenu();
                break;

            case 3:
                multiplication.multiplication();
                layout.finalMenu();
                break;

            case 4:
                division.division();
                layout.finalMenu();
                break;

            default:
                System.out.println("We are unable to identify your choice. Try again!");
        }

        scan.close();
    }
}
