package db.dbsettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DbPropertiesReader implements IProperties{


    @Override
    public Map<String, String> read() {
        Properties properties = new Properties();
        Map<String,String> settings = new HashMap<>();


        try {
            properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/sql.properties"));

            for (Map.Entry entry: properties.entrySet()){
                settings.put((String) entry.getKey(), (String) entry.getValue());
            }
            return settings;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
