package ua.kpi.epam.transport.commands.stop;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.StopDao;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Stop;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class FindAllStopsOnRouteCommand implements StopCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        StopDao stopDao = DaoFactory.getInstance().createStopDao();

        List<Stop> stop = stopDao.findAllStopsOnRoute(new Route(Integer
                .valueOf(request.getParameter(ID_ATTRIBUTE)), null));

        request.setAttribute(RESULT_LIST_ATTRIBUTE, stop);
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }
}
