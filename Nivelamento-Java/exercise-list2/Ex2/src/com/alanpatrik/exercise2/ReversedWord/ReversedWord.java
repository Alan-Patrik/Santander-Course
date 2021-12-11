package com.alanpatrik.exercise2.ReversedWord;

public class ReversedWord {
    public void reversedWord(char[] characterWord) {
        String reversedWord = "";
        for (int i = characterWord.length - 1; i >= 0; i--) {
            reversedWord += characterWord[i];
        }

        System.out.println(reversedWord);
    }
}
