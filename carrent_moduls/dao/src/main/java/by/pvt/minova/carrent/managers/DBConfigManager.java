package by.pvt.minova.carrent.managers;

import by.pvt.minova.carrent.constants.DBConfigConstant;

import java.util.ResourceBundle;

public class DBConfigManager {
    private final ResourceBundle bundle = ResourceBundle.getBundle(DBConfigConstant.DATABASE_SOURCE);
    private static DBConfigManager instance;

    private DBConfigManager(){}

    public static synchronized DBConfigManager getInstance(){
        if(instance == null){
            instance = new DBConfigManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }
}
