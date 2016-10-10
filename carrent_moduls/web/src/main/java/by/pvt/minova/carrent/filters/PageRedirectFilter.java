package by.pvt.minova.carrent.filters;

import by.pvt.minova.carrent.constants.PagePath;
import by.pvt.minova.carrent.managers.ConfigurationManager;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageRedirectFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // переход на стартовую страницу
        httpResponse.sendRedirect(httpRequest.getContextPath() + ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH));
        chain.doFilter(request, response);
    }

    public void destroy() {}
}