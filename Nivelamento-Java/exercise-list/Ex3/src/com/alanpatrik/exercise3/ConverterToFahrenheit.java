package com.alanpatrik.exercise3;

import com.alanpatrik.exercise3.entities.CelsiusToFahrenheitConverter;
import com.alanpatrik.exercise3.layout.Layout;

import java.util.Scanner;

public class ConverterToFahrenheit {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Layout layout = new Layout();
        CelsiusToFahrenheitConverter convertFahrenheit = new CelsiusToFahrenheitConverter();
        double celsius;

        layout.mainMenu();
        celsius = scan.nextDouble();
        convertFahrenheit.celsiusToFahrenheitConverter(celsius);
        scan.close();
    }
}
