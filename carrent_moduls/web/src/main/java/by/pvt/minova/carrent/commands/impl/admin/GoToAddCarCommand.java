package by.pvt.minova.carrent.commands.impl.admin;

import by.pvt.minova.carrent.commands.AbstractCommand;
import by.pvt.minova.carrent.constants.PagePath;
import by.pvt.minova.carrent.managers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class GoToAddCarCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(PagePath.ADMIN_ADD_CAR_PAGE_PATH);
        return page;
    }
}