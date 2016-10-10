package by.pvt.minova.carrent.constants;

public class SqlRequest {
    public static final String ADD_USER = "INSERT INTO users(first_name, last_name, aid, login, password) VALUES (?, ?, ?, ?, ?)";
    public static final String CHECK_LOGIN = "SELECT login FROM users WHERE login = ?";
    public static final String CHECK_AUTHORIZATION = "SELECT login, password FROM users WHERE login = ? AND password = ?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE uid = ?";
    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE uid = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    public static final String GET_ALL_CLIENTS = "SELECT first_name, last_name FROM users WHERE access_level = 0 ORDER BY last_name";

    public static final String ADD_ACCOUNT_WITH_ID = "INSERT INTO accounts (aid, status) VALUES (?, ?)";
    public static final String GET_ALL_ACCOUNTS = "SELECT * FROM accounts";
    public static final String GET_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE aid = ?";
    public static final String DELETE_ACCOUNT_BY_ID = "DELETE FROM accounts WHERE aid = ?";

    public static final String GET_ALL_CARS = "SELECT number, model, year, price FROM cars";
    public static final String GET_CAR_BY_ID = "SELECT * FROM cars WHERE cid = ?";
    public static final String ADD_CAR = "INSERT INTO cars(number, model, year, price) VALUES (?, ?, ?, ?)";
    public static final String DELETE_CAR_BY_ID = "DELETE FROM cars WHERE cid = ?";

    public static final String CHANGE_STATUS = "UPDATE accounts SET status = ? WHERE aid = ?";
    public static final String CHECK_ACCOUNT_STATUS = "SELECT status FROM accounts WHERE aid = ?";
    public static final String GET_BLOCKED_ACCOUNTS = "SELECT * FROM accounts WHERE status = 1";

    private SqlRequest() {}
}