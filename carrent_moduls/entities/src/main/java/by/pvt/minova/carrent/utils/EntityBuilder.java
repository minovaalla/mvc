package by.pvt.minova.carrent.utils;

import by.pvt.minova.carrent.entities.Account;
import by.pvt.minova.carrent.entities.User;
import by.pvt.minova.carrent.entities.Car;

public class EntityBuilder {
    private EntityBuilder(){}

    /**
     * Creates account
       * @param status:  0 - unblock, 1 - blocked)
     * @return entity of <b>account</b>
     */
   public static Account buildAccount(int id, int status){
        Account account = new Account();
        account.setId(id);
        account.setStatus(status);
        return account;
    }

     /**
     * Creates user
     * @param id - user's id
     * @param firstName - user's first name
     * @param lastName user's last name
     * @param aid - account's id
     * @param login - user's login
     * @param password - user's password
     * @param accessLevel - user's access level (0 - client, 1 - administrator)
     * @return entity of <b>User</b>
     */
    public static User buildUser(int id, String firstName, String lastName, int aid, String login, String password, int accessLevel){
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAccountId(aid);
        user.setLogin(login);
        user.setPassword(password);
        user.setAccessLevel(accessLevel);
        return user;
    }


    public static Car buildCar(int id, String number, String model, String year, String price){
        Car car = new Car();
        car.setId(id);
        car.setNumber(number);
        car.setModel(model);
        car.setYear(year);
        car.setPrice(price);
        return car;
    }

}
