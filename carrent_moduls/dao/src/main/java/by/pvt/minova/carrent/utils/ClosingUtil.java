package by.pvt.minova.carrent.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ClosingUtil {

    private ClosingUtil(){}

    public static void close(PreparedStatement statement){
        if(statement != null){
            try{
                statement.close();
            }
            catch(SQLException e){
                CarrentLogger.getInstance().logError(ClosingUtil.class, e.getMessage());
            }
        }
    }

    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            }
            catch(SQLException e){
                CarrentLogger.getInstance().logError(ClosingUtil.class, e.getMessage());
            }
        }
    }
}
