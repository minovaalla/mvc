package by.pvt.minova.carrent.managers;

import java.util.ResourceBundle;

import by.pvt.minova.carrent.constants.ConfigConstant;

public class MessageManager {
    private final ResourceBundle bundle = ResourceBundle.getBundle(ConfigConstant.MESSAGES_SOURCE);
    private static MessageManager instance;

    private MessageManager(){}

    public static synchronized MessageManager getInstance(){
        if(instance == null){
            instance = new MessageManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }
}
