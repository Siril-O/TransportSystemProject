package ua.kpi.epam.transport.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.commands.route.RouteCommand;

/**
 * Servlet Filter implementation class RouteStopsFilter
 */
@WebFilter("/admin/route.jsp")
public class RouteStopFilter implements Filter {


	/**
	 * Default constructor.
	 */
	public RouteStopFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
     * @param request
     * @param chain
     * @param response
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		req.setAttribute(RouteCommand.STOP_LIST_ATTRIBUTE, DaoFactory.getInstance().createStopDao().findAll());

		chain.doFilter(request, response);
	}

	/**
     * @param fConfig
     * @throws javax.servlet.ServletException
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
