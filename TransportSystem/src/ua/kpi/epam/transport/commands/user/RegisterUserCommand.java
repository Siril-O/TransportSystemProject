package ua.kpi.epam.transport.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import ua.kpi.epam.transport.commands.Command;
import ua.kpi.epam.transport.commands.validators.user.RegisterUserCommandValidator;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.UserDao;
import ua.kpi.epam.transport.entities.User;
import ua.kpi.epam.transport.entities.enums.UserRole;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class RegisterUserCommand implements Command {

    /**
     *
     */
    public static final String LOGIN_ATTRIBUTE = "login";

    /**
     *
     */
    public static final String PASSWORD_ATTRIBUTE = "password";

    /**
     *
     */
    public static final String CONFIRM_PASSWORD_ATTRIBUTE = "confirmPassword";

    /**
     *
     */
    public static final String NAME_ATTRIBUTE = "name";

    /**
     *
     */
    public static final String SURNAME_ATTRIBUTE = "surname";

    /**
     *
     */
    public static final String ROLE_ATTRIBUTE = "role";

    /**
     *
     */
    public static final String RESULT_ATTRIBUTE = "result";

    /**
     *
     */
    public static final String DESTINATION_PAGE = "./registration.jsp";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String REGISTER_USER_ERROR_MSG = "RegisterUserError";
    private static final String REGISTER_USER_SUCCESSFUL_MSG = "RegisterUserSuccessful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new RegisterUserCommandValidator().validate(request, response)) {
            return;
        }

        UserDao userDao = DaoFactory.getInstance().createUserDao();

        String login = request.getParameter(LOGIN_ATTRIBUTE);

        String password = request.getParameter(PASSWORD_ATTRIBUTE);

        if (userDao.findByLogin(login) == null) {
            userDao.create(new User(
                    login,
                    request.getParameter(NAME_ATTRIBUTE),
                    request.getParameter(SURNAME_ATTRIBUTE),
                    password,
                    UserRole.valueOf(request.getParameter(ROLE_ATTRIBUTE).toUpperCase())));

            request.setAttribute(RESULT_ATTRIBUTE,
                    LocalizationHelper.getInstanse().
                    getLocalizedMessage(request, REGISTER_USER_SUCCESSFUL_MSG) + login);

        } else {
            request.setAttribute(RESULT_ATTRIBUTE,
                    LocalizationHelper.getInstanse().
                    getLocalizedMessage(request, REGISTER_USER_ERROR_MSG) + login);
        }
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}
