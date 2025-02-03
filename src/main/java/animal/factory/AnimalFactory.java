package animal.factory;

import animal.base.Animal;
import animal.cat.Cat;
import animal.dog.Dog;
import animal.duck.Duck;

public class AnimalFactory {
    public static Animal create(String type, String name, int age, double weight, String color) {
        switch (type.toLowerCase()) {
            case "cat":
                return new Cat(name, age, weight, color);
            case "dog":
                return new Dog(name, age, weight, color);
            case "duck":
                return new Duck(name, age, weight, color);
            default:
                throw new IllegalArgumentException("Неизвестный тип животного");
        }
    }
}