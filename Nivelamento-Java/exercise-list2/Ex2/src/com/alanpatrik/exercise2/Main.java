package com.alanpatrik.exercise2;

import com.alanpatrik.exercise2.ReversedWord.ReversedWord;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ReversedWord reversedWord = new ReversedWord();
        boolean isFinished = false;

        do {
            System.out.print("Enter a word: ");
            String word = scanner.nextLine();

            char[] characterWord = word.toCharArray();

            reversedWord.reversedWord(characterWord);

            System.out.print("Do you want to insert any more words?[S][N] ");
            String yesOrNo = scanner.nextLine();

            isFinished = yesOrNo.equalsIgnoreCase("n") ? true
                    : yesOrNo.equalsIgnoreCase("s") ? false
                    : true;
        } while (isFinished != true);
    }
}
