package by.pvt.minova.carrent.managers;

import java.util.ResourceBundle;

import by.pvt.minova.carrent.constants.ConfigConstant;

public class ConfigurationManager {
    private final ResourceBundle bundle = ResourceBundle.getBundle(ConfigConstant.CONFIGS_SOURCE);
    private static ConfigurationManager instance;

    private ConfigurationManager(){}

    public static synchronized ConfigurationManager getInstance(){
        if(instance == null){
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getProperty(String key){
        return bundle.getString(key);
    }
}
