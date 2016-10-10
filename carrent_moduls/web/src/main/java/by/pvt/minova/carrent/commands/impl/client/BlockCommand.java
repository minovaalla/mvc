package by.pvt.minova.carrent.commands.impl.client;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.minova.carrent.constants.UserType;
import by.pvt.minova.carrent.commands.AbstractCommand;
import by.pvt.minova.carrent.constants.*;
import by.pvt.minova.carrent.entities.User;
import by.pvt.minova.carrent.exceptions.ServiceException;
import by.pvt.minova.carrent.managers.ConfigurationManager;
import by.pvt.minova.carrent.managers.MessageManager;
import by.pvt.minova.carrent.services.impl.AccountServiceImpl;
import by.pvt.minova.carrent.commands.factory.CommandType;
import by.pvt.minova.carrent.utils.RequestParameterParser;

public class BlockCommand extends AbstractCommand {
    private User user;
    private String description;
    private CommandType commandType;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        UserType userType = RequestParameterParser.getUserType(request);
        if(userType == UserType.CLIENT){
            user = RequestParameterParser.getRecordUser(request);
            commandType = RequestParameterParser.getCommandType(request);
            description = commandType.getValue();
            try {
                if(!AccountServiceImpl.getInstance().checkAccountStatus(user.getAccountId())){
                    AccountServiceImpl.getInstance().blockAccount(user, description);
                    request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.SUCCESS_OPERATION));
                    page = ConfigurationManager.getInstance().getProperty(PagePath.CLIENT_BLOCK_PAGE_PATH);
                }
                else{
                    page = ConfigurationManager.getInstance().getProperty(PagePath.CLIENT_BLOCK_PAGE_PATH);
                }
            }
            catch (ServiceException | SQLException e) {
                page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
            }
        }
        else{
            page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
            session.invalidate();
        }
        return page;
    }
}
