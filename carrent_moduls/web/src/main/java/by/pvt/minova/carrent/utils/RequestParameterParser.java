package by.pvt.minova.carrent.utils;

import by.pvt.minova.carrent.commands.factory.CommandType;
import by.pvt.minova.carrent.constants.Parameters;
import by.pvt.minova.carrent.constants.UserType;
import by.pvt.minova.carrent.entities.Account;
import by.pvt.minova.carrent.entities.User;
import by.pvt.minova.carrent.entities.Car;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RequestParameterParser {
    private RequestParameterParser() {}

    public static User getUser(HttpServletRequest request){
        int id = 0;
        if (request.getParameter(Parameters.USER_ID) != null){
            id = Integer.valueOf(request.getParameter(Parameters.USER_ID));
        }
        int accountId = 0;
        if (request.getParameter(Parameters.ACCOUNT_ID) != null){
            accountId = Integer.valueOf(request.getParameter(Parameters.ACCOUNT_ID));
        }
        int accessLevel = 0;
        if (request.getParameter(Parameters.USER_ACCESS_LEVEL) != null){
            accessLevel = Integer.valueOf(request.getParameter(Parameters.USER_ACCESS_LEVEL));
        }
        String firstName = request.getParameter(Parameters.USER_FIRST_NAME);
        String lastName = request.getParameter(Parameters.USER_LAST_NAME);
        String login = request.getParameter(Parameters.USER_LOGIN);
        String password = request.getParameter(Parameters.USER_PASSWORD);
        User user = EntityBuilder.buildUser(id, firstName, lastName, accountId, login, password, accessLevel);
        return user;
    }

    public static Car getCar(HttpServletRequest request){
        int id = 0;
        if (request.getParameter(Parameters.CAR_ID) != null){
            id = Integer.valueOf(request.getParameter(Parameters.CAR_ID));
        }
        String number = request.getParameter(Parameters.CAR_NUMBER);
        String model = request.getParameter(Parameters.CAR_MODEL);
        String year = request.getParameter(Parameters.CAR_YEAR);
        String price = request.getParameter(Parameters.CAR_PRICE);
        Car car = EntityBuilder.buildCar(id, number, model, year, price);
        return car;
    }

    public static Account getAccount(HttpServletRequest request) throws NumberFormatException {
       int id = Integer.valueOf(request.getParameter(Parameters.ACCOUNT_ID));
       int status = 0;
        if (request.getParameter(Parameters.ACCOUNT_STATUS) != null){
            status = Integer.valueOf(request.getParameter(Parameters.ACCOUNT_STATUS));
        }

        Account account = EntityBuilder.buildAccount(id, status);
        return account;
    }

    public static UserType getUserType(HttpServletRequest request) {
        return (UserType) request.getSession().getAttribute(Parameters.USERTYPE);
    }

    public static List<User> getUserList(HttpServletRequest request) {
        return (List<User>) request.getSession().getAttribute(Parameters.USER_LIST);
    }

    public static List<Account> getAccountsList(HttpServletRequest request) {
        return (List<Account>) request.getSession().getAttribute(Parameters.ACCOUNTS_LIST);
    }

    public static List<Car> getCarList(HttpServletRequest request) {
        return (List<Car>) request.getSession().getAttribute(Parameters.CAR_LIST);
    }

    public static User getRecordUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(Parameters.USER);
    }

    public static Car getRecordCar(HttpServletRequest request) {
        return (Car) request.getSession().getAttribute(Parameters.CAR);
    }


    public static CommandType getCommandType(HttpServletRequest request){
        String commandName = request.getParameter(Parameters.COMMAND);
        CommandType commandType = CommandType.LOGIN;
        if(commandName != null) {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        }
        return commandType;
    }

   }
