package com.alanpatrik.exercise1.entities;

public class ShoppingCartService {

    public ShoppingCartService() {
    }

    public void getAllShoppingCart(String[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }
}
