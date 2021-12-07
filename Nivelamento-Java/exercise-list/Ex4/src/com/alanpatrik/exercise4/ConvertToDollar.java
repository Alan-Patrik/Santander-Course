package com.alanpatrik.exercise4;

import com.alanpatrik.exercise4.entities.ConvertRealToDollar;
import com.alanpatrik.exercise4.layout.Layout;

import java.util.Scanner;

public class ConvertToDollar {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Layout layout = new Layout();
        ConvertRealToDollar convertRealToDollar = new ConvertRealToDollar();
        double dollar;
        double real;

        layout.mainMenu();
        convertRealToDollar.userInteration1();
        dollar = scan.nextDouble();
        convertRealToDollar.userInteration2();
        real = scan.nextFloat();
        convertRealToDollar.convertToDollar(dollar, real);
        scan.close();
    }
}
