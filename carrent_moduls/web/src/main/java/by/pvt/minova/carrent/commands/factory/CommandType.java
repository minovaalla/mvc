package by.pvt.minova.carrent.commands.factory;

import by.pvt.minova.carrent.commands.ICommand;
import by.pvt.minova.carrent.commands.impl.admin.GoBackAdminCommand;
import by.pvt.minova.carrent.commands.impl.admin.GoToUnblockCommand;
import by.pvt.minova.carrent.commands.impl.admin.ShowClientsCommand;
import by.pvt.minova.carrent.commands.impl.admin.UnblockCommand;
import by.pvt.minova.carrent.commands.impl.client.ViewCarsCommand;
import by.pvt.minova.carrent.commands.impl.client.BalanceCommand;
import by.pvt.minova.carrent.commands.impl.client.BlockCommand;
import by.pvt.minova.carrent.commands.impl.client.GoBackClientCommand;
import by.pvt.minova.carrent.commands.impl.admin.GoToAddCarCommand;
import by.pvt.minova.carrent.commands.impl.admin.AddCarCommand;
import by.pvt.minova.carrent.commands.impl.user.GoBackCommand;
import by.pvt.minova.carrent.commands.impl.user.GoToRegistrationCommand;
import by.pvt.minova.carrent.commands.impl.user.LoginUserCommand;
import by.pvt.minova.carrent.commands.impl.user.LogoutUserCommand;
import by.pvt.minova.carrent.commands.impl.user.RegistrationCommand;

public enum CommandType {
    //user commands
    LOGIN, LOGOUT, REGISTRATION, GOTOREGISTRATION, BACK,

    // client commands
    VIEWCARS, BALANCE, BLOCK, BACKCLIENT,

    // admin commands
    ADDCAR, GOTOADDCAR, CLIENTS, UNBLOCK, GOTOUNBLOCK, BACKADMIN;

    public ICommand getCurrentCommand() throws EnumConstantNotPresentException{
        switch(this){
            case LOGIN:
                return new LoginUserCommand();

            case LOGOUT:
                return new LogoutUserCommand();

            case REGISTRATION:
                return new RegistrationCommand();

            case GOTOREGISTRATION:
                return new GoToRegistrationCommand();

            case BACK:
                return new GoBackCommand();

            case VIEWCARS:
                return new ViewCarsCommand();

            case BALANCE:
                return new BalanceCommand();

           case BLOCK:
                return new BlockCommand();

            case BACKCLIENT:
                return new GoBackClientCommand();

            case ADDCAR:
                return new AddCarCommand();

            case GOTOADDCAR:
                return new GoToAddCarCommand();

            case CLIENTS:
                return new ShowClientsCommand();

            case UNBLOCK:
                return new UnblockCommand();

            case GOTOUNBLOCK:
                return new GoToUnblockCommand();

            case BACKADMIN:
                return new GoBackAdminCommand();

            default:
                return new LoginUserCommand();
                //throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

    public String getValue(){
        switch(this){
            case BLOCK:
                return "Блокировка личного кабинета";

            case UNBLOCK:
                return "Разблокировка личного кабинета";

            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }
}
