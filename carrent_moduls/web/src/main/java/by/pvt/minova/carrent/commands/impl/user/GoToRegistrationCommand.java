/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.pvt.minova.carrent.commands.impl.user;

import javax.servlet.http.HttpServletRequest;

import by.pvt.minova.carrent.commands.AbstractCommand;
import by.pvt.minova.carrent.constants.PagePath;
import by.pvt.minova.carrent.managers.ConfigurationManager;

/**
 * @author minova
 * @version 1.0
 *
 */
public class GoToRegistrationCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
        return page;
    }
}
