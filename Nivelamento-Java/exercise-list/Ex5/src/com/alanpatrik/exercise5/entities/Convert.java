package com.alanpatrik.exercise5.entities;

import java.util.Scanner;

public class Convert {
    private final double INCH = 2.54;

    public void inchToCentimeterConverter(double inch) {
        double result = inch * INCH;
        System.out.println("|                                                      |");
        System.out.println("| " + inch + " [in] => " + result + " [cm]                               |");
        System.out.println("|                                                      |");
        System.out.println("|======================================================|");
    }
}
