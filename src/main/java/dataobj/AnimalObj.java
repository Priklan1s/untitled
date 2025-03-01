package dataobj;

import animal.base.Animal;

public class AnimalObj {

    private String name;
    private int age;


    public AnimalObj(String name, int age){

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
