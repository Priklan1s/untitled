package db.tables;

import animal.base.Animal;
import dataobj.AnimalObj;
import db.dbconnector.IDBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalTable extends AbsTable {

    public AnimalTable(IDBConnector idbConnector){
        super("animal",idbConnector);
    }



    public void createAnimal(Animal animal){
        if (!this.isTableExist()){
            this.createTable();

        }
        Map<String,String> data = new HashMap<>();
        data.put("color", animal.getColor());
        data.put("id", String.valueOf(animal.getId()));
        data.put("name",animal.getName());
        data.put("weight", String.valueOf(animal.getWeight()));
        data.put("age", String.valueOf(animal.getAge()));

        this.insert(data);

    }

    public List<AnimalObj> listAnimals(String... predicate ){
        List<AnimalObj> animals = new ArrayList<>();
        ResultSet resultSet = this.list(predicate);


        try {
            while (resultSet.next()) {
                AnimalObj animalObj = new AnimalObj(
                        resultSet.getString("name"),
                        resultSet.getInt("age")
            );
                animals.add(animalObj);
            }
            return animals;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

}
