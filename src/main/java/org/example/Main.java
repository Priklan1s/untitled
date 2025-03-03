package org.example;

import animal.base.Animal;
import animal.factory.AnimalFactory;
import animal.enums.Command;
import dataobj.AnimalObj;
import db.dbconnector.IDBConnector;
import db.dbconnector.MySqlConnector;
import db.tables.AnimalTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      List<Animal> animals = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        IDBConnector idbConnector = new MySqlConnector();
        AnimalTable absTable = new AnimalTable(idbConnector);




        while (true) {
            System.out.print("Введите команду (add/list/exit/update/filter): ");
            String commandInput = scanner.nextLine().trim().toLowerCase();

            try {
                Command command = Command.valueOf(commandInput.toUpperCase());
                switch (command) {
                    case ADD:
                        addAnimal(animals, scanner, absTable);
                        break;
                    case LIST:
                        List<Animal>animalList =absTable.listAnimals();

                        for (Animal animal : animalList)
                        {
                            System.out.println(animal.toString());
                        };
                        break;
                    case EXIT:
                        System.out.println("Выход из программы.");
                        idbConnector.close();
                        System.exit(0);
                    case  UPDATE:
                        updateAnimal(animals,scanner,absTable);
                        break;
                    case FILTER:
                        System.out.print("Введите тип животного: ");
                        String type = scanner.nextLine();
                        List<Animal> filtredByType =absTable.filterByType(type);
                        if (filtredByType.isEmpty()){
                            System.out.println("Никого нет по типу животного: " + type);
                            break;
                        }
                        for (Animal animal : filtredByType)
                        {
                            System.out.println(animal.toString());
                        };
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Неизвестная команда.");
            }
        }
    }

    private static void addAnimal(List<Animal> animals, Scanner scanner, AnimalTable absTable) {
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

            int age = 0;
            while (true) {
                try {
                    System.out.print("Введите возраст: ");
                    age = Integer.parseInt(scanner.nextLine());
                    if (age < 0) {
                        throw new IllegalArgumentException("Возраст не может быть отрицательным числом.");
                    }
                    break; // Выход из цикла, если возраст корректен
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Неверный формат числа. Пожалуйста, введите число.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            double weight = 0;
            while (true) {
                try {
                    System.out.print("Введите вес: ");
                    weight = Double.parseDouble(scanner.nextLine());
                    if (weight < 0) {
                        throw new IllegalArgumentException("Вес не может быть отрицательным числом.");
                    }
                    break; // Выход из цикла, если вес корректен
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Неверный формат числа. Пожалуйста, введите число.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            System.out.print("Введите цвет: ");
            String color = scanner.nextLine().trim();

            // Проверка цвета на наличие цифр
            if (!color.matches("[a-zA-Zа-яА-ЯёЁ\\s]+")) {
                throw new IllegalArgumentException("Цвет не должен содержать цифры или специальные символы.");
            }

            Animal animal = AnimalFactory.create(animalType, name, age, weight, color);
            animals.add(animal);
            absTable.createAnimal(animal,animalType);
            System.out.println("Животное добавлено.");
            animal.say();
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private static void updateAnimal(List<Animal> animals, Scanner scanner, AnimalTable absTable) {
        String animalType = null;
        String id;
        System.out.print("Введите id животного которое хотите обновить: ");
        id = scanner.nextLine().trim().toLowerCase();
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

            int age = 0;
            while (true) {
                try {
                    System.out.print("Введите возраст: ");
                    age = Integer.parseInt(scanner.nextLine());
                    if (age < 0) {
                        throw new IllegalArgumentException("Возраст не может быть отрицательным числом.");
                    }
                    break; // Выход из цикла, если возраст корректен
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Неверный формат числа. Пожалуйста, введите число.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            double weight = 0;
            while (true) {
                try {
                    System.out.print("Введите вес: ");
                    weight = Double.parseDouble(scanner.nextLine());
                    if (weight < 0) {
                        throw new IllegalArgumentException("Вес не может быть отрицательным числом.");
                    }
                    break; // Выход из цикла, если вес корректен
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Неверный формат числа. Пожалуйста, введите число.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            System.out.print("Введите цвет: ");
            String color = scanner.nextLine().trim();

            // Проверка цвета на наличие цифр
            if (!color.matches("[a-zA-Zа-яА-ЯёЁ\\s]+")) {
                throw new IllegalArgumentException("Цвет не должен содержать цифры или специальные символы.");
            }

            Animal animal = AnimalFactory.create(animalType, name, age, weight, color);
            absTable.updateAnimal(id,animal,animalType);
            updateListAnimals(animals,id,animal);
            System.out.println("Животное обновлено.");
            animal.say();
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
    private static void updateListAnimals (List<Animal> animals, String id, Animal animal ){
        Animal existAnimal= null;

        for (Animal animal1 : animals) {
            if (animal1.getId().equals(id)){
                existAnimal = animal1;
                break;
            }

        }
        if (existAnimal != null){
            animals.remove(existAnimal);
            animals.add(animal);
        }
    }


    private static void listAnimals(List<Animal> animals) {
        if (animals.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
    }
}
