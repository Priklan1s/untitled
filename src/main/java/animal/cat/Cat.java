package animal.cat;

import animal.base.Animal;

public class Cat extends Animal {
    public Cat(String name, int age, double weight, String color) {
        super(name, age, weight, color, null);
    }

    @Override
    public void say() {
        System.out.println("Мяу");
    }
}