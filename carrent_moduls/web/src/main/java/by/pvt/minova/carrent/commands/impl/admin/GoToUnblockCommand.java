/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.pvt.minova.carrent.commands.impl.admin;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.minova.carrent.constants.UserType;
import by.pvt.minova.carrent.commands.AbstractCommand;
import by.pvt.minova.carrent.constants.*;
import by.pvt.minova.carrent.entities.Account;
import by.pvt.minova.carrent.exceptions.ServiceException;
import by.pvt.minova.carrent.managers.ConfigurationManager;
import by.pvt.minova.carrent.managers.MessageManager;
import by.pvt.minova.carrent.services.impl.AccountServiceImpl;
import by.pvt.minova.carrent.utils.RequestParameterParser;

public class GoToUnblockCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        UserType userType = RequestParameterParser.getUserType(request);
        if(userType == UserType.ADMINISTRATOR){
            try {
                List<Account> list = AccountServiceImpl.getInstance().getBlockedAccounts();
                session.setAttribute(Parameters.ACCOUNTS_LIST, list);
                page = ConfigurationManager.getInstance().getProperty(PagePath.ADMIN_UNBLOCK_PAGE);
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
