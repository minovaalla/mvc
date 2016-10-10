/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.pvt.minova.carrent.commands.impl.user;

import javax.servlet.http.HttpServletRequest;

import by.pvt.minova.carrent.commands.AbstractCommand;
import by.pvt.minova.carrent.constants.PagePath;
import by.pvt.minova.carrent.managers.ConfigurationManager;

/**
 *
 * @author minova
 * @version 1.0
 *
 */
public class GoBackCommand extends AbstractCommand {

    /**
     *
     * @param request - http request
     * @return index.jsp page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
        return page;
    }
}
