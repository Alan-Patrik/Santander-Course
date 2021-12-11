package com.alanpatrik.exercise4;

import com.alanpatrik.exercise4.entities.FindBiggerSmallerAverage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        FindBiggerSmallerAverage findBiggerSmallerAverage = new FindBiggerSmallerAverage();
        int[] num = new int[5];
        boolean isFinished = false;
        String yesOrNo;

        System.out.println("Enter 5 numbers..");
        do {
            for (int i = 0; i <= num.length - 1; i++) {
                System.out.print("Number " + (i + 1) + ": ");
                num[i] = scan.nextInt();
            }

            findBiggerSmallerAverage.findBiggerSmallerAverage(num);

            System.out.println("Want to add more numbers?[S][N] ");
            yesOrNo = scan.next();

            isFinished = !yesOrNo.equalsIgnoreCase("s");

        } while (isFinished != true);
    }
}
