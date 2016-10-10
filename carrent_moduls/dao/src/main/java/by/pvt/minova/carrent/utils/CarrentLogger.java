package by.pvt.minova.carrent.utils;

import org.apache.log4j.Logger;

public class CarrentLogger {
    private Logger logger;
    private static CarrentLogger instance;

    private CarrentLogger(){}

    public static synchronized CarrentLogger getInstance(){
        if(instance == null){
            instance = new CarrentLogger();
        }
        return instance;
    }

    public void logError(Class sender, String message){
        logger = Logger.getLogger(sender);
        logger.error(message);
    }


}