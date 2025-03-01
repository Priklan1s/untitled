package db.dbconnector;

import db.dbsettings.DbPropertiesReader;

import java.sql.*;
import java.util.Map;

public class MySqlConnector implements IDBConnector{

    private Map<String,String> settings;
    private static Connection connection = null;
    private static Statement statement = null;

    public MySqlConnector(){
        settings = new DbPropertiesReader().read();
        try {
            this.getConnect();
        }catch (SQLException e) {
            throw new RuntimeException(e);}
    }

    private void getConnect() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(settings.get("url"), settings.get("username"), settings.get("password"));
        }
        if(statement == null){
            statement = connection.createStatement();
        }
}

    @Override
    public void execute(String sqlRequest) throws SQLException {
        System.out.println(sqlRequest);
        statement.execute(sqlRequest);

    }

    @Override
    public ResultSet executeQuery(String sqlRequest) throws SQLException {

        return statement.executeQuery(sqlRequest);

    }

    @Override
    public void close() {
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
