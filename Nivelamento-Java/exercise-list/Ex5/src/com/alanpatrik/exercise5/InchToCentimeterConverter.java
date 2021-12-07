package com.alanpatrik.exercise5;

import com.alanpatrik.exercise5.entities.Convert;
import com.alanpatrik.exercise5.layout.Layout;

import java.util.Scanner;

public class InchToCentimeterConverter {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Layout layout = new Layout();
        Convert convert = new Convert();
        double inch;

        layout.mainMenu();
        inch = scan.nextDouble();
        convert.inchToCentimeterConverter(inch);
        scan.close();
    }
}
