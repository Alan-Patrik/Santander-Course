package com.alanpatrik.exercise;

import com.alanpatrik.exercise.entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        String name = "";
        int age = 0;
        boolean isFinished = false;
        String yesOrNo;

        System.out.println("Enter 5 names..");

        do {
            for (int i = 0; i < 3; i++) {
                System.out.print("Name: ");
                name = scan.next();

                System.out.print("Height: ");
                float height = scan.nextFloat();

                System.out.print("Weight: ");
                float weight = scan.nextFloat();

                persons.add(new Person(name, height, weight));
            }

            person.bodyMassIndex(persons);

            System.out.print("Want to add more numbers?[S][N] ");
            yesOrNo = scan.next();

            isFinished = !yesOrNo.equalsIgnoreCase("s");

        } while (isFinished != true);
    }
}
