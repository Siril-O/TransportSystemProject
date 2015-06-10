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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.kpi.epam.transport.entities.User;
import ua.kpi.epam.transport.entities.enums.UserRole;
import ua.kpi.epam.transport.commands.user.AutentificateUserCommand;

/**
 * Servlet Filter implementation class PrivilegeFilter
 */
@WebFilter("/admin/*")
public class PrivilegeFilter implements Filter {

	private static final String ERROR_PAGE = "/privilegeError.jsp";

	/**
	 * Default constructor.
	 */
	public PrivilegeFilter() {
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
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		if (session == null) {
			resp.sendRedirect(req.getContextPath()
					+ AutentificateUserCommand.RESULT_PAGE);
		} else {
			User user = ((User) session
					.getAttribute(AutentificateUserCommand.USER_SESSION_ATTRIBUTE));
			if (user == null) {
				resp.sendRedirect(req.getContextPath()
						+ AutentificateUserCommand.RESULT_PAGE);
			} else {
				if (user.getRole() != UserRole.ADMIN) {
					resp.sendRedirect(req.getContextPath() + ERROR_PAGE);
				} else {
					chain.doFilter(request, response);
				}
			}
		}
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
