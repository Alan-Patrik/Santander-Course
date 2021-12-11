package com.alanpatrik.exercise6;

import com.alanpatrik.exercise6.entities.LargestName;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LargestName largestName = new LargestName();
        boolean isFinished = false;

        do {
            System.out.print("Enter a word: ");
            String word = scanner.nextLine();

            char[] nameList = word.toCharArray();

            largestName.largestName(nameList);

            System.out.print("Do you want to insert any more words?[S][N] ");
            String yesOrNo = scanner.nextLine();

            isFinished = yesOrNo.equalsIgnoreCase("n") || !yesOrNo.equalsIgnoreCase("s");
        } while (isFinished != true);
    }
}
