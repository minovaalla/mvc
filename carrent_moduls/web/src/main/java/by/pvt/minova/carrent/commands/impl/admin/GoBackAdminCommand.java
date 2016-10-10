package by.pvt.minova.carrent.commands.impl.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.minova.carrent.commands.AbstractCommand;
import by.pvt.minova.carrent.constants.PagePath;
import by.pvt.minova.carrent.constants.UserType;
import by.pvt.minova.carrent.managers.ConfigurationManager;
import by.pvt.minova.carrent.utils.RequestParameterParser;

public class GoBackAdminCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        UserType userType = RequestParameterParser.getUserType(request);
        if(userType == UserType.ADMINISTRATOR){
            page = ConfigurationManager.getInstance().getProperty(PagePath.ADMIN_PAGE_PATH);
        }
        else{
            page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
            session.invalidate();
        }
        return page;
    }
}
