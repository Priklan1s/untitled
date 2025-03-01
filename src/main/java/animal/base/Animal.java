package animal.base;

public abstract class Animal {
    private int id;
    private String name;
    private int age;
    private double weight;
    private String color;

    public Animal(String name, int age, double weight, String color) {
        this.name = name;
        setAge(age); // Используем сеттер для проверки корректности
        setWeight(weight);
        this.color = color;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Возраст не может быть отрицательным");
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0) throw new IllegalArgumentException("Вес не может быть отрицательным");
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract void say(); // Абстрактный метод

    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    @Override
    public String toString() {
        return "Привет! меня зовут " + name +
                ", мне " + age + " " + getFormattedAge() +
                ", я вешу - " + weight + " кг, мой цвет - " + color;
    }

    private String getFormattedAge() {
        if (age == 0) return "лет"; // Для возраста 0 используем "лет"
        int lastDigit = age % 10; // Последняя цифра
        int lastTwoDigits = age % 100; // Последние две цифры

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            return "лет"; // Случай 11-14 лет
        }

        if (lastDigit == 1) {
            return "год"; // Случай оканчивающийся на 1 (кроме 11)
        }

        if (lastDigit >= 2 && lastDigit <= 4) {
            return "года"; // Случай оканчивающийся на 2, 3, 4 (кроме 12, 13, 14)
        }

        return "лет"; // Все остальные случаи
    }
}