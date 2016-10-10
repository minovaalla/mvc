package by.pvt.minova.carrent.services.impl;

import by.pvt.minova.carrent.constants.UserType;
import by.pvt.minova.carrent.dao.impl.AccountDaoImpl;
import by.pvt.minova.carrent.dao.impl.UserDaoImpl;
import by.pvt.minova.carrent.entities.Account;
import by.pvt.minova.carrent.entities.User;
import by.pvt.minova.carrent.exceptions.DaoException;
import by.pvt.minova.carrent.exceptions.ServiceException;
import by.pvt.minova.carrent.services.AbstractService;
import by.pvt.minova.carrent.managers.PoolManager;
import by.pvt.minova.carrent.utils.CarrentLogger;

import java.sql.SQLException;
import java.util.List;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class UserServiceImpl extends AbstractService<User> {
    private static UserServiceImpl instance;

    private UserServiceImpl(){}

    public static synchronized UserServiceImpl getInstance(){
        if(instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    /**
     * Calls Dao add() method
     *
     * @param entity - object of derived class Entity
     * @throws SQLException
     */
    @Override
    public void add(User entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls Dao getAll() method
     *
     * @return list of objects of derived class Entity
     * @throws SQLException
     */
    @Override
    public List<User> getAll() throws SQLException, ServiceException {
        List<User> users = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            users = UserDaoImpl.getInstance().getAll();
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return users;
    }

    /**
     * Calls Dao getById() method
     *
     * @param id - id of entity
     * @return object of derived class Entity
     * @throws SQLException
     */
    @Override
    public User getById(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls Dao update() method
     *
     * @param entity - object of derived class Entity
     * @throws SQLException
     */
    @Override
    public void update(User entity) throws SQLException {
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

    public boolean checkUserAuthorization(String login, String password) throws SQLException, ServiceException {
        boolean isAuthorized = false;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            isAuthorized = UserDaoImpl.getInstance().isAuthorized(login, password);
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return isAuthorized;
    }

    public User getUserByLogin(String login) throws SQLException, ServiceException {
        User user = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            user = UserDaoImpl.getInstance().getByLogin(login);
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return user;
    }

    public UserType checkAccessLevel(User user) throws SQLException{
        UserType userType;
        if(UserType.CLIENT.ordinal() == user.getAccessLevel()){
            userType = UserType.CLIENT;
        }
        else{
            userType = UserType.ADMINISTRATOR;
        }
        return userType;
    }

    public boolean checkIsNewUser(User user) throws SQLException, ServiceException {
        boolean isNew = false;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            if ((AccountDaoImpl.getInstance().getById(user.getAccountId()) == null) & (UserDaoImpl.getInstance().isNewUser(user.getLogin()))) {
                isNew = true;
            }
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        //PoolManager.getInstance().releaseConnection(connection);
        return isNew;
    }

    public void registrateUser(User user, Account account) throws SQLException, ServiceException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            AccountDaoImpl.getInstance().add(account);
            UserDaoImpl.getInstance().add(user);
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
