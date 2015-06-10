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

import ua.kpi.epam.transport.commands.schedule.ScheduleCommand;
import ua.kpi.epam.transport.dao.jdbc.JdbcDaoFactory;

/**
 * Servlet Filter implementation class ScheduleFilter
 */
@WebFilter("/admin/schedule.jsp")
public class ScheduleFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ScheduleFilter() {
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
     * @param response
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		req.setAttribute(ScheduleCommand.RESULT_ROUTES_LIST, JdbcDaoFactory
				.getInstance().createRouteDao().findAll());

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
