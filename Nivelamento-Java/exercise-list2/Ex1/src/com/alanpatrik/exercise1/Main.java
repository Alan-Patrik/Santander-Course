package com.alanpatrik.exercise1;

import com.alanpatrik.exercise1.entities.ShoppingCartService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ShoppingCartService service = new ShoppingCartService();
        String[] list = new String[5];

        System.out.println("Enter the name of 5 fruits..");
        for(int i = 0; i <= list.length -1; i++) {
            list[i] = scan.nextLine();
        }

        service.getAllShoppingCart(list);
    }
}
