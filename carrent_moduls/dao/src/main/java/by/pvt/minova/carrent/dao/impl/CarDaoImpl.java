package by.pvt.minova.carrent.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.pvt.minova.carrent.constants.ColumnName;
import by.pvt.minova.carrent.constants.SqlRequest;
import by.pvt.minova.carrent.dao.AbstractDao;
import by.pvt.minova.carrent.exceptions.DaoException;
import by.pvt.minova.carrent.managers.PoolManager;
import by.pvt.minova.carrent.utils.ClosingUtil;
import by.pvt.minova.carrent.entities.Car;
import by.pvt.minova.carrent.utils.EntityBuilder;
import by.pvt.minova.carrent.utils.CarrentLogger;

public class CarDaoImpl extends AbstractDao<Car> {
    private static CarDaoImpl instance;
    static String message;

    private CarDaoImpl(){}

    public static synchronized CarDaoImpl getInstance(){
        if(instance == null){
            instance = new CarDaoImpl();
        }
        return instance;
    }

    @Override
    public void add(Car car) throws DaoException {
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.ADD_CAR);
            statement.setString(1, car.getNumber());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getYear());
            statement.setString(4,car.getPrice());
            statement.executeUpdate();
        }
        catch (SQLException e){
            message = "Unable to add the user account ";
            CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(statement);
        }
    }

    @Override
    public List<Car> getAll() throws DaoException {
        List<Car> list = new ArrayList<>();
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_CARS);
            result = statement.executeQuery();
            while (result.next()) {
                Car car = new Car();
                car.setNumber(result.getString(ColumnName.CAR_NUMBER));
                car.setModel(result.getString(ColumnName.CAR_MODEL));
                car.setYear(result.getString(ColumnName.CAR_YEAR));
                car.setPrice(result.getString(ColumnName.CAR_PRICE));
                list.add(car);
            }
        }
        catch (SQLException e){
            message = "Unable to return list of users ";
            CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return list;
    }

    @Override
    public Car getById(int id) throws DaoException {
        Car car = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_CAR_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                car = buildCar(result);
            }
        }
        catch (SQLException e){
            message = "Unable to return the user ";
            CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return car;
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_CAR_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            message = "Unable to delete the user ";
            CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(statement);
        }
    }

    private Car buildCar(ResultSet result) throws SQLException{
        int id = result.getInt(ColumnName.CAR_ID);
        String number = result.getString(ColumnName.CAR_NUMBER);
        String model = result.getString(ColumnName.CAR_MODEL);
        String year = result.getString(ColumnName.CAR_YEAR);
        String price = result.getString(ColumnName.CAR_PRICE);
        Car car = EntityBuilder.buildCar(id, number, model, year, price);
        return car;
    }
}
