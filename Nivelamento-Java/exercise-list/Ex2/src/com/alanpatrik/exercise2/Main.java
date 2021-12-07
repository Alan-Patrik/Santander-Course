package com.alanpatrik.exercise2;

import com.alanpatrik.exercise2.entities.MultiplicationTable;
import com.alanpatrik.exercise2.layout.Layout;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Layout layout = new Layout();
        MultiplicationTable multiplicationTable = new MultiplicationTable();

        layout.mainMenu();
        int number = scan.nextInt();
        multiplicationTable.multiplicationTable(number);
        layout.finalMenu();
        scan.close();
    }
}
