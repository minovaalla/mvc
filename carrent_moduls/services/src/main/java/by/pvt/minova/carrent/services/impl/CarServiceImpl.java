package by.pvt.minova.carrent.services.impl;


import by.pvt.minova.carrent.dao.impl.CarDaoImpl;
import by.pvt.minova.carrent.entities.Car;
import by.pvt.minova.carrent.exceptions.DaoException;
import by.pvt.minova.carrent.exceptions.ServiceException;
import by.pvt.minova.carrent.managers.PoolManager;
import by.pvt.minova.carrent.services.AbstractService;
import by.pvt.minova.carrent.utils.CarrentLogger;


import java.sql.SQLException;
import java.util.List;

public class CarServiceImpl extends AbstractService<Car> {
    private static CarServiceImpl instance;

    private CarServiceImpl(){}

    public static synchronized CarServiceImpl getInstance(){
        if(instance == null){
            instance = new CarServiceImpl();
        }
        return instance;
    }

    /**
     * Calls Dao add() method
     *
     */
    @Override
    public void add(Car car) throws SQLException, ServiceException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            CarDaoImpl.getInstance().add(car);
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }
    /**
     * Calls Dao getAll() method
     *
     * @return list of objects of derived class Entity
     * @throws SQLException
     */
    @Override
    public List<Car> getAll() throws SQLException, ServiceException {
        List<Car> cars = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            cars = CarDaoImpl.getInstance().getAll();
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return cars;
    }

    /**
     * Calls Dao getById() method
     *
     * @param id - id of entity
     * @return object of derived class Entity
     * @throws SQLException
     */
    @Override
    public Car getById(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls Dao update() method
     *
     * @param entity - object of derived class Entity
     * @throws SQLException
     */
    @Override
    public void update(Car entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls Dao delete() method
     *
     * @param id - id of entity
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void registrateCar(Car car) throws SQLException, ServiceException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            CarDaoImpl.getInstance().add(car);
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }
   }
