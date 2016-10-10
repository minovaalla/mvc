package by.pvt.minova.carrent.managers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import by.pvt.minova.carrent.exceptions.DaoException;
import by.pvt.minova.carrent.utils.CarrentLogger;
import org.apache.commons.dbcp2.BasicDataSource;

public class PoolManager {
    private static PoolManager instance;
    private BasicDataSource dataSource;

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    private PoolManager() {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(bundle.getString("database.driver"));
        dataSource.setUsername(bundle.getString("database.user"));
        dataSource.setPassword(bundle.getString("database.password"));
        dataSource.setUrl(bundle.getString("database.url"));
    }

    public static synchronized PoolManager getInstance(){
        if(instance == null){
            instance = new PoolManager();
        }
        return instance;
    }

    private Connection connect() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    public Connection getConnection() throws DaoException {
        try {
            if (connectionHolder.get() == null) {
                Connection connection = connect();
                connectionHolder.set(connection);
            }
        }
        catch(SQLException e){
            String message = "Unable to get connection";
            throw new DaoException(message, e);
        }
        return connectionHolder.get();
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            try{
                connection.close();
                connectionHolder.remove();
            }
            catch(SQLException e){
                CarrentLogger.getInstance().logError(getClass(), e.getMessage());
            }
        }
    }
}