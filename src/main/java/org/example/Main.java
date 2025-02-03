package org.example;

import animal.base.Animal;
import animal.factory.AnimalFactory;
import animal.enums.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите команду (add/list/exit): ");
            String commandInput = scanner.nextLine().trim().toLowerCase();

            try {
                Command command = Command.valueOf(commandInput.toUpperCase());
                switch (command) {
                    case ADD:
                        addAnimal(animals, scanner);
                        break;
                    case LIST:
                        listAnimals(animals);
                        break;
                    case EXIT:
                        System.out.println("Выход из программы.");
                        System.exit(0);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Неизвестная команда.");
            }
        }
    }

    private static void addAnimal(List<Animal> animals, Scanner scanner) {
        String animalType = null;

        // Валидация типа животного
        while (true) {
            System.out.print("Введите тип животного (cat/dog/duck): ");
            animalType = scanner.nextLine().trim().toLowerCase();
            if (animalType.equals("cat") || animalType.equals("dog") || animalType.equals("duck")) {
                break;
            }
            System.out.println("Ошибка: Неизвестный тип животного. Попробуйте ещё раз.");
        }

        try {
            System.out.print("Введите имя: ");
            String name = scanner.nextLine().trim();

            // Проверка имени на наличие цифр
            if (!name.matches("[a-zA-Zа-яА-ЯёЁ]+")) {
                throw new IllegalArgumentException("Имя не должно содержать цифры или специальные символы.");
            }

            System.out.print("Введите возраст: ");
            int age = Integer.parseInt(scanner.nextLine());

            // Проверка возраста на положительность
            if (age < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным числом.");
            }

            System.out.print("Введите вес: ");
            double weight = Double.parseDouble(scanner.nextLine());

            // Проверка веса на положительность
            if (weight < 0) {
                throw new IllegalArgumentException("Вес не может быть отрицательным числом.");
            }

            System.out.print("Введите цвет: ");
            String color = scanner.nextLine().trim();

            // Проверка цвета на наличие цифр
            if (!color.matches("[a-zA-Zа-яА-ЯёЁ\\s]+")) {
                throw new IllegalArgumentException("Цвет не должен содержать цифры или специальные символы.");
            }

            Animal animal = AnimalFactory.create(animalType, name, age, weight, color);
            animals.add(animal);
            System.out.println("Животное добавлено.");
            animal.say();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат числа.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private static void listAnimals(List<Animal> animals) {
        if (animals.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}