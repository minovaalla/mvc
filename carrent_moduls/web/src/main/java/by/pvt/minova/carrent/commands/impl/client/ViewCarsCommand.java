package by.pvt.minova.carrent.commands.impl.client;


import by.pvt.minova.carrent.commands.AbstractCommand;
import by.pvt.minova.carrent.constants.MessageConstants;
import by.pvt.minova.carrent.constants.PagePath;
import by.pvt.minova.carrent.constants.Parameters;
import by.pvt.minova.carrent.constants.UserType;
import by.pvt.minova.carrent.entities.Car;
import by.pvt.minova.carrent.exceptions.ServiceException;
import by.pvt.minova.carrent.managers.ConfigurationManager;
import by.pvt.minova.carrent.managers.MessageManager;
import by.pvt.minova.carrent.services.impl.CarServiceImpl;
import by.pvt.minova.carrent.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class ViewCarsCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        UserType userType = RequestParameterParser.getUserType(request);
        if(userType == UserType.CLIENT){
            try{
                List<Car> list = CarServiceImpl.getInstance().getAll();
                session.setAttribute(Parameters.CAR_LIST, list);
                page = ConfigurationManager.getInstance().getProperty(PagePath.CLIENT_VIEW_CARS_PAGE_PATH);
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