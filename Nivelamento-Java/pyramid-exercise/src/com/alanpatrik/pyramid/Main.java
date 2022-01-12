package com.alanpatrik.pyramid;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int userChoice;

        System.out.print("Digite a altura da piramide: ");
        userChoice = scan.nextInt();
        start1(userChoice);
        start2(userChoice);

    }

    public static void start1(int number) {
        int row = 0;
        int space = 0;
        int count = 0;
        int columnCount = 0;

        for (row = 1; row <= number; row++) {
            System.out.println("linha");
            for (space = 0; space <= (number - row); space++) {
                columnCount++;
                System.out.println(columnCount);
//                System.out.print(" ");
            }
            for (count = 1; count <= row; count++) {
                System.out.println("haha");
//                System.out.print("* ");
            }

            System.out.println();
        }

    }

    public static void start2(int number) {
        int count = 1;

        while (count <= number) {

            for (int i = 1; i <= (number - count); i++) {
                System.out.print(" ");
            }

            for (int x = 1; x <= count; x++) {
                System.out.print(" *");
            }

            count += 1;

            System.out.println();

        }
    }
}
