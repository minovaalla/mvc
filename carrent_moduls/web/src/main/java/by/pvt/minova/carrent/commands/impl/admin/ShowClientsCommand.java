package by.pvt.minova.carrent.commands.impl.admin;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.minova.carrent.constants.UserType;
import by.pvt.minova.carrent.constants.*;
import by.pvt.minova.carrent.entities.User;
import by.pvt.minova.carrent.exceptions.ServiceException;
import by.pvt.minova.carrent.services.impl.UserServiceImpl;
import by.pvt.minova.carrent.commands.AbstractCommand;
import by.pvt.minova.carrent.utils.RequestParameterParser;
import by.pvt.minova.carrent.managers.ConfigurationManager;
import by.pvt.minova.carrent.managers.MessageManager;

public class ShowClientsCommand extends AbstractCommand{

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        UserType userType = RequestParameterParser.getUserType(request);
        if(userType == UserType.ADMINISTRATOR){
            try{
                List<User> list = UserServiceImpl.getInstance().getAll();
                session.setAttribute(Parameters.USER_LIST, list);
                page = ConfigurationManager.getInstance().getProperty(PagePath.ADMIN_SHOW_CLIENTS_PAGE);
            }
            catch (ServiceException | SQLException e) {
                page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
            }
        }
        // TODO ПРОВверить, возможно отработает фильтр
        else{
            page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
            session.invalidate();
        }
        return page;
    }
}
