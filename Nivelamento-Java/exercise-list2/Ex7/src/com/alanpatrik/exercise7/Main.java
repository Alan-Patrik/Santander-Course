package com.alanpatrik.exercise7;

import com.alanpatrik.exercise7.entities.Person;

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
            for (int i = 0; i < 5; i++) {
                System.out.print("Name: ");
                name = scan.next();

                System.out.print("Age: ");
                age = scan.nextInt();

                persons.add(new Person(name, age));
            }

            person.younger(persons);
            person.older(persons);
            person.rumAverage(persons);

            System.out.print("Want to add more numbers?[S][N] ");
            yesOrNo = scan.next();

            isFinished = !yesOrNo.equalsIgnoreCase("s");

        } while (isFinished != true);
    }
}
