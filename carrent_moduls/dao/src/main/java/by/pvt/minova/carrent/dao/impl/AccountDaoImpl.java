package by.pvt.minova.carrent.dao.impl;

import by.pvt.minova.carrent.constants.ColumnName;
import by.pvt.minova.carrent.utils.ClosingUtil;
import by.pvt.minova.carrent.dao.AbstractDao;
import by.pvt.minova.carrent.entities.Account;
import by.pvt.minova.carrent.constants.SqlRequest;
import by.pvt.minova.carrent.exceptions.DaoException;
import by.pvt.minova.carrent.managers.PoolManager;
import by.pvt.minova.carrent.utils.EntityBuilder;
import by.pvt.minova.carrent.utils.CarrentLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl extends AbstractDao<Account> {
    private static AccountDaoImpl instance;
    static String message;

    private AccountDaoImpl(){}

    /**
     * Singleton method
     * @return entity of <b>AccountDaoImpl</b>
     */
    public static synchronized AccountDaoImpl getInstance(){
        if(instance == null){
            instance = new AccountDaoImpl();
        }
        return instance;
    }

    /**
     * Adds new account to the storage
     * @param account - entity of <b>Account</b>
     * @throws DaoException
     */
    @Override
    public void add(Account account) throws DaoException {
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.ADD_ACCOUNT_WITH_ID);
            statement.setInt(1, account.getId());
            statement.setInt(2, account.getStatus());
            statement.executeUpdate();
        }
        catch (SQLException e){
            message = "Unable to add the account ";
            CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(statement);
        }
    }

    /**
     * Gets list of accounts from the storage
     * @return list of accounts
     * @throws DaoException
     */
    @Override
    public List<Account> getAll() throws DaoException {
        List<Account> list = new ArrayList<>();
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_ACCOUNTS);
            result = statement.executeQuery();
            while (result.next()) {
                Account account = buildAccount(result);
                list.add(account);
            }
        }
        catch (SQLException e){
            message = "Unable to return list of accounts ";
            CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return list;
    }

    /**
     * Gets account by account's id from the storage
     * @param id - account's id
     * @return entity of <b>Account</b>
     * @throws DaoException
     */
    @Override
    public Account getById(int id) throws DaoException{
        Account account = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ACCOUNT_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();

            while (result.next()) {
                account = buildAccount(result);
            }
        }
        catch(SQLException e){
            message = "Unable to return the account ";
            CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return account;
    }

    /**
     * Checks if account is blocked
     * @param id - account's id
     * @return true - if account is blocked, false - otherwise
     * @throws DaoException
     */
    public boolean isAccountStatusBlocked(int id) throws DaoException{
        boolean isBlocked = false;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.CHECK_ACCOUNT_STATUS);
            statement.setDouble(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                if (result.getInt("status") == 1) {
                    isBlocked = true;
                }
            }
        }
        catch(SQLException e){
            message = "Unable to check account status ";
            CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return isBlocked;
    }

    /**
     * Gets list of blocked accounts from the storage
     * @return list of blocked accounts
     * @throws DaoException
     */
    public List<Account> getBlockedAccounts() throws DaoException{
        List<Account> list = new ArrayList<>();
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_BLOCKED_ACCOUNTS);
            result = statement.executeQuery();
            while (result.next()) {
                Account account = buildAccount(result);
                list.add(account);
            }
        }
        catch(SQLException e){
            message = "Unable to return list of blocked accounts ";
           CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return list;
    }

     /**
     * Updates account
     * @param id - account's id
     * @param status - account's status
     * @throws DaoException
     */

    public void updateAccountStatus(int id, int status) throws DaoException{
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.CHANGE_STATUS);
            statement.setInt(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            message = "Unable to update account status ";
            CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(statement);
        }
    }

    /**
     * Deletes account from the storage
     * @param id - account's id
     * @throws DaoException
     */
    @Override
    public void delete(int id)throws DaoException{
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_ACCOUNT_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            message = "Unable to delete the account ";
           CarrentLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(statement);
        }
    }

    /**
     * Builds account from ResultSet object
     * @param result - ResultSet object
     * @return entity of <b>Account</b>
     * @throws SQLException
     */
    private Account buildAccount(ResultSet result) throws SQLException{
        int id = result.getInt(ColumnName.ACCOUNT_ID);
        int status = result.getInt(ColumnName.ACCOUNT_STATUS);
        Account account = EntityBuilder.buildAccount(id, status);
        return account;
    }
}