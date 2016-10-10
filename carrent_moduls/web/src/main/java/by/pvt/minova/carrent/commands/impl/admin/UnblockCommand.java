package by.pvt.minova.carrent.commands.impl.admin;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.minova.carrent.constants.AccountStatus;
import by.pvt.minova.carrent.constants.UserType;
import by.pvt.minova.carrent.commands.AbstractCommand;
import by.pvt.minova.carrent.constants.*;
import by.pvt.minova.carrent.entities.Account;
import by.pvt.minova.carrent.exceptions.ServiceException;
import by.pvt.minova.carrent.services.impl.AccountServiceImpl;
import by.pvt.minova.carrent.utils.RequestParameterParser;
import by.pvt.minova.carrent.managers.ConfigurationManager;
import by.pvt.minova.carrent.managers.MessageManager;

public class UnblockCommand extends AbstractCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        UserType userType = RequestParameterParser.getUserType(request);
        if(userType == UserType.ADMINISTRATOR){
            try{
                //TODO переделать
                int aid = Integer.valueOf(request.getParameter(Parameters.OPERATION_UNBLOCK));
                AccountServiceImpl.getInstance().updateAccountStatus(aid, AccountStatus.UNBLOCKED);
                List<Account> list = AccountServiceImpl.getInstance().getBlockedAccounts();
                session.setAttribute(Parameters.ACCOUNTS_LIST, list);
                page = ConfigurationManager.getInstance().getProperty(PagePath.ADMIN_UNBLOCK_PAGE);
            }
            catch(NumberFormatException e){
                List<Account> list = RequestParameterParser.getAccountsList(request);
                if(!list.isEmpty()){
                    request.setAttribute(Parameters.ERROR_EMPTY_CHOICE, MessageManager.getInstance().getProperty(MessageConstants.EMPTY_CHOICE));
                    page = ConfigurationManager.getInstance().getProperty(PagePath.ADMIN_UNBLOCK_PAGE);
                }
                else{
                    request.setAttribute(Parameters.ERROR_EMPTY_LIST, MessageManager.getInstance().getProperty(MessageConstants.EMPTY_LIST));
                    page = ConfigurationManager.getInstance().getProperty(PagePath.ADMIN_UNBLOCK_PAGE);
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
