package by.pvt.minova.carrent.commands.impl.client;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.minova.carrent.constants.UserType;
import by.pvt.minova.carrent.commands.AbstractCommand;
import by.pvt.minova.carrent.constants.*;
import by.pvt.minova.carrent.entities.Account;
import by.pvt.minova.carrent.entities.User;
import by.pvt.minova.carrent.exceptions.ServiceException;
import by.pvt.minova.carrent.managers.MessageManager;
import by.pvt.minova.carrent.services.impl.AccountServiceImpl;
import by.pvt.minova.carrent.utils.RequestParameterParser;
import by.pvt.minova.carrent.managers.ConfigurationManager;

public class BalanceCommand extends AbstractCommand {
    private User user;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        UserType userType = RequestParameterParser.getUserType(request);
        if(userType == UserType.CLIENT){
            user = RequestParameterParser.getRecordUser(request);
            try {
                Account account = AccountServiceImpl.getInstance().getById(user.getAccountId());
                page = ConfigurationManager.getInstance().getProperty(PagePath.CLIENT_BALANCE_PAGE_PATH);
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
