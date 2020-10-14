package model;

public class Person {
    private String name;
    private int age;
    private int height;
    private int weight;
    private Goal goal;
    private Goals goals;
    private Exercise exercise;
    private ExerciseList exercises;

    public Person(String name, int age, int height, int weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

}
