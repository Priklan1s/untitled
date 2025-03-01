package db.tables;

import animal.base.Animal;
import animal.factory.AnimalFactory;
import db.dbconnector.IDBConnector;
import db.dbconnector.MySqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbsTable {

    private String tableName;
    private IDBConnector idbConnector;


    public boolean isTableExist(){
        try {
            ResultSet resultSet =this.idbConnector.executeQuery(String.format("show tables;"));

            while (resultSet.next()){
               if (resultSet.getString(1).equals(tableName))
               {
                return true;
                }
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;

    }

    public AbsTable(String tableName, IDBConnector idbConnector) {
        this.tableName = tableName;
        this.idbConnector = idbConnector;
    }

    public void createTable() {
        try {
            this.idbConnector.execute(String.format("create table %s values (int primary key id, varchar(10) name, int age, int weight, varchar(10) color)", tableName));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteTable() {
        try {
            this.idbConnector.execute(String.format("drop table %s", tableName));
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    public ResultSet list( String... predicates) {

        String requestPredicates = predicates.length == 0 ? "" : String.format("where %s", String.join("and", predicates));

        try {
            return idbConnector.executeQuery(String.format("select * from %s %s", tableName, requestPredicates));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Map<String, String> data) {

        String columsNames = String.join(",", data.keySet());
        String valueData = String.join(",", data.values());


        try {
            idbConnector.execute(String.format("insert into %s (%s) values (%s)", tableName, columsNames, valueData));
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void update(Map<String, String> data ,String... predicates){
        String predicatesStr = "";

        if(predicates.length != 0){
            predicatesStr = String.join(" and ",predicates);
        }

        List<String> columsValuesStr = new ArrayList<>();
        for (Map.Entry<String,String> entry: data.entrySet()){
            columsValuesStr.add(String.format("%s=%s",entry.getKey(),entry.getValue()));
        }

        try {
            idbConnector.execute(String.format("update %s  set %s %s", tableName, String.join(",",columsValuesStr), predicatesStr));
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }



}
