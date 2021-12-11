package com.alanpatrik.exercise3;

import com.alanpatrik.exercise3.entities.OddAndEven;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        OddAndEven oddAndEven = new OddAndEven();
        int[] num = new int[5];
        boolean isFinished = false;
        String yesOrNo;

        System.out.println("Enter 5 numbers..");
        do {
            for (int i = 0; i <= num.length - 1; i++) {
                System.out.print("Number " + (i + 1) + ": ");
                num[i] = scan.nextInt();
            }

            oddAndEven.oddAndEven(num);

            System.out.println("Want to add more numbers?[S][N] ");
            yesOrNo = scan.next();

            isFinished = !yesOrNo.equalsIgnoreCase("s");

        } while (isFinished != true);
    }
}
