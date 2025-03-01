package animal.dog;

import animal.base.Animal;

public class Dog extends Animal {
    public Dog(String name, int age, double weight, String color) {
        super(name, age, weight, color, null);
    }

    @Override
    public void say() {
        System.out.println("Гав");
    }
}