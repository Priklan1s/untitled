package db.tables;

import animal.base.Animal;
import dataobj.AnimalObj;
import db.dbconnector.IDBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AnimalTable extends AbsTable {

    public AnimalTable(IDBConnector idbConnector){
        super("animal",idbConnector);
    }



    public void createAnimal(Animal animal, String animalType){
        if (!this.isTableExist()){
            this.createTable();

        }
        Map<String,String> data = new HashMap<>();
        data.put("color", "'" + animal.getColor() + "'");
        data.put("name","'" + animal.getName() + "'");
        data.put("weight", String.valueOf(animal.getWeight()));
        data.put("age", String.valueOf(animal.getAge()));
        data.put("type","'" + animalType + "'");
        data.put("id","'" + UUID.randomUUID() + "'");


        this.insert(data);

    }

    public List<Animal> listAnimals(String... predicate ){
        List<Animal> animals = new ArrayList<>();
        ResultSet resultSet = this.list(predicate);


        try {
            while (resultSet.next()) {
                Animal animalObj = new Animal(
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("weight"),
                        resultSet.getString("color"),
                        resultSet.getString("id")

                ) {
                    @Override
                    public void say() {

                    }
                };
                animals.add(animalObj);
            }
            return animals;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }


    public void updateAnimal(String id,Animal animal,String animalType){
        Map<String,String> data = new HashMap<>();
        data.put("color", "'" + animal.getColor() + "'");
        data.put("name","'" + animal.getName() + "'");
        data.put("weight", String.valueOf(animal.getWeight()));
        data.put("age", String.valueOf(animal.getAge()));
        data.put("type","'" + animalType + "'");


        this.update(data, "'" +  id +"'");


    }

    public List<Animal> filterByType(String type){
        ResultSet resultSet = this.list(String.format(" type = %s","'" + type + "'" ));
        List<Animal> animals = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                Animal animalObj = new Animal(
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("weight"),
                        resultSet.getString("color"),
                        resultSet.getString("id")

                ) {
                    @Override
                    public void say() {

                    }
                };
                animals.add(animalObj);
            }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }

        return animals;
    }

}
