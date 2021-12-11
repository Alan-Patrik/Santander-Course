package com.alanpatrik.exercise5;

import com.alanpatrik.exercise5.entities.FindLargestName;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        FindLargestName findLargestName = new FindLargestName();
        String[] nameList = new String[5];
        String name = "";
        boolean isFinished = false;
        String yesOrNo;

        System.out.println("Enter 5 names..");

        do {
            for (int i = 0; i <= nameList.length - 1; i++) {
                System.out.print("Name " + (i + 1) + ": ");
                name = scan.nextLine();
                nameList[i] = name;
            }

            findLargestName.findLargestName(nameList);

            System.out.print("Want to add more numbers?[S][N] ");
            yesOrNo = scan.next();

            isFinished = !yesOrNo.equalsIgnoreCase("s");

        } while (isFinished != true);
    }
}
