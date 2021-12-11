package com.alanpatrik.exercise.entities;

import java.util.List;

public class Person {

    private String name;
    private float height;
    private float weight;

    public Person() {
    }

    public Person(String name, float height, float weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public void bodyMassIndex(List<Person> personList) {
        float imcResult;

        for (Person person : personList) {
            imcResult = imc(person.getWeight(), person.getHeight());
            if (imcResult < 18.5) {
                System.out.println(person.getName() + " is underweight!");

            } else if (imcResult >= 18.5 && imcResult <= 25) {
                System.out.println(person.getName() + " is at his ideal weight!");

            } else if (imcResult > 25) {
                System.out.println(person.getName() + " is overweight!");
            }
        }

    }

    public float imc(float weight, float height) {
        float newImc = weight / (height * height);
        return newImc;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", height=" + height + ", weight=" + weight + '}';
    }
}
