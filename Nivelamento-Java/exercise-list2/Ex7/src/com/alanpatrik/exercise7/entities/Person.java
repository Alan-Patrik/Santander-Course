package com.alanpatrik.exercise7.entities;

import java.util.List;

public class Person {

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void younger(List<Person> personList) {
        Person newPerson = new Person();
        int auxAge = 0;

        for (Person person : personList) {
            if (person.getAge() > auxAge) {
                auxAge = person.getAge();
                for (Person person2 : personList) {
                    if (person2.getAge() < auxAge) {
                        auxAge = person2.getAge();
                        newPerson = person2;
                    }

                }
            }
        }

        System.out.println(newPerson);
    }

    public void older(List<Person> personList) {
        Person newPerson = new Person();
        int auxAge = 0;
        for (Person person : personList) {
            if (person.getAge() > auxAge) {
                auxAge = person.getAge();
                newPerson = person;
            }
        }

        System.out.println(newPerson);
    }

    public void rumAverage(List<Person> personList) {
        float sum = 0;
        float average = 0;

        for (Person person : personList) {
            sum += person.getAge();
        }

        average = (sum / personList.size());
        System.out.printf("Average => %.2f \n", average);
    }


    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
