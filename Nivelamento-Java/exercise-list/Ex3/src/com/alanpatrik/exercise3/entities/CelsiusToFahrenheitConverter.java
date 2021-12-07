package com.alanpatrik.exercise3.entities;

import java.util.Locale;

public class CelsiusToFahrenheitConverter {

    public void celsiusToFahrenheitConverter(double c) {
        double result = (c * 1.8d) + 32;
        System.out.println("|                                                      |");
        System.out.println("| Your temperature in Fahrenheit is...                 |");
        System.out.println("| " + result + "F                                                |");
        System.out.println("|                                                      |");
        System.out.println("|======================================================|");
    }
}
