package by.pvt.minova.carrent.services.impl;

import by.pvt.minova.carrent.constants.AccountStatus;
import by.pvt.minova.carrent.exceptions.ServiceException;
import by.pvt.minova.carrent.services.AbstractService;
import by.pvt.minova.carrent.dao.impl.AccountDaoImpl;
import by.pvt.minova.carrent.entities.Account;
import by.pvt.minova.carrent.entities.User;
import by.pvt.minova.carrent.exceptions.DaoException;
import by.pvt.minova.carrent.managers.PoolManager;
import by.pvt.minova.carrent.utils.CarrentLogger;

import java.sql.SQLException;
import java.util.List;

public class AccountServiceImpl extends AbstractService<Account> {
    private static AccountServiceImpl instance;
    private AccountServiceImpl(){}

    public static synchronized AccountServiceImpl getInstance(){
        if(instance == null){
            instance = new AccountServiceImpl();
        }
        return instance;
    }

    /**
     * Calls AccountDaoImpl add() method
     *
     * @param entity - Account object
     * @throws SQLException
     */
    @Override
    public void add(Account entity) throws ServiceException, SQLException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            AccountDaoImpl.getInstance().add(entity);
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
     * Calls AccountDaoImpl getAll() method
     *
     * @return list of Account objects
     * @throws SQLException
     */
    @Override
    public List<Account> getAll() throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls AccountDaoImpl getById() method
     *
     * @param id - Account id
     * @return Account object
     * @throws SQLException
     */
    @Override
    public Account getById(int id) throws SQLException, ServiceException {
        Account account = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            account = AccountDaoImpl.getInstance().getById(id);
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return account;
    }

    /**
     * Calls AccountDaoImpl update() method
     *
     * @param entity - Account object
     * @throws SQLException
     */
    @Override
    public void update(Account entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls AccountDaoImpl delete() method
     *
     * @param id - Account id
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public List<Account> getBlockedAccounts() throws SQLException, ServiceException {
        List<Account> accounts = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            accounts = AccountDaoImpl.getInstance().getBlockedAccounts();
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return accounts;
    }


    public void updateAccountStatus(int id, int status) throws SQLException, ServiceException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            AccountDaoImpl.getInstance().updateAccountStatus(id, status);
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean checkAccountStatus(int id) throws SQLException, ServiceException{
        boolean isBlocked = false;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            isBlocked = AccountDaoImpl.getInstance().isAccountStatusBlocked(id);
            connection.commit();
            CarrentLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            CarrentLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return isBlocked;
    }

   public void blockAccount(User user, String description) throws SQLException, ServiceException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            AccountDaoImpl.getInstance().updateAccountStatus(user.getAccountId(), AccountStatus.BLOCKED);
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
