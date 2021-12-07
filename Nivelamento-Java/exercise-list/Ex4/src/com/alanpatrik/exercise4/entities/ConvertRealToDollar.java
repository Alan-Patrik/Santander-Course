package com.alanpatrik.exercise4.entities;

import java.util.Locale;

public class ConvertRealToDollar {

    public void userInteration1() {
        System.out.print("|");
        System.out.print("  Enter the dollar rate $: ");
    }

    public void userInteration2() {
        System.out.print("|");
        System.out.print("  Enter the amount in reais R$: ");
    }

    public void convertToDollar(double dollar, double real) {
        double convertedDollar;

        convertedDollar = (real / dollar);
        System.out.print("|  R$");
        System.out.printf(Locale.getDefault(), "%.2f", real);
        System.out.print(" => $");
        System.out.printf(Locale.ENGLISH, "%.2f", convertedDollar);
        System.out.print("                                    |\n");
        System.out.println("|                                                      |");
        System.out.println("|======================================================|");
    }
}
