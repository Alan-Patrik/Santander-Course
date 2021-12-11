package com.alanpatrik.exercise6.entities;

public class LargestName {
    public void largestName(char[] nameList) {
        String name = "";
        for (int i = 0; i <= nameList.length - 1; i++) {
            if (i % 2 != 0) {
                name += Character.toLowerCase(nameList[i]);

            } else {
                name += Character.toUpperCase(nameList[i]);
            }
        }

        System.out.println(name);
    }
}
