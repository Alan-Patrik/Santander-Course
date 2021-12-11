package com.alanpatrik.exercise5.entities;

public class FindLargestName {
    public void findLargestName(String[] nameList) {
        String largestName = "";
        for (int i = 0; i <= nameList.length - 1; i++) {
            if (nameList[i].length() > largestName.length()) {
                largestName = nameList[i];
            }
        }
        System.out.println("Largest namea => " + largestName);
    }
}
