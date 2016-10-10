package by.pvt.minova.carrent.commands.factory;

import javax.servlet.http.HttpServletRequest;

import by.pvt.minova.carrent.commands.ICommand;
import by.pvt.minova.carrent.commands.impl.user.LoginUserCommand;
import by.pvt.minova.carrent.utils.RequestParameterParser;

public class CommandFactory {
    private static CommandFactory instance;

    private CommandFactory(){}

    public static synchronized CommandFactory getInstance(){
        if(instance == null){
            instance = new CommandFactory();
        }
        return instance;
    }

    public ICommand defineCommand(HttpServletRequest request){
        ICommand current = null;
        try{
            CommandType type = RequestParameterParser.getCommandType(request);
            current = type.getCurrentCommand();
        }
        catch(IllegalArgumentException e){
            current = new LoginUserCommand();
        }
        return current;
    }
}
