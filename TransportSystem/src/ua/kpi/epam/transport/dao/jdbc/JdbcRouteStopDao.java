package ua.kpi.epam.transport.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

import ua.kpi.epam.transport.dao.RouteStopDao;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.RouteStop;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import ua.kpi.epam.transport.extras.LoggerHelper;

/**
 *
 * @author KIRIL
 */
public class JdbcRouteStopDao implements RouteStopDao {

    private static final String FIND_ALL_ROUTE_STOP_QUERY = "SELECT * FROM route_stop";
    private static final String UPDATE_ROUTE_STOP_QUERY = "UPDATE route_stop SET name=?  WHERE id=?";
    private static final String FIND_ROUTE_STOP_BY_ID_QUERY = "SELECT * FROM route_stop WHERE id=?";
    private static final String FIND_ROUTE_STOP_BY_ROUTE_QUERY = "SELECT * FROM route_stop WHERE route_id=? ORDER BY number ASC";
    private static final String ROUTE_STOP_CREATION_QUERY = "INSERT INTO route_stop (route_id, stop_id, number)  VALUES (?,?,?)";
    private static final String DELETE_ROUTE_STOP_BY_ROUTE_AND_STOP_QUERY = "DELETE FROM route_stop WHERE route_id=? AND stop_id=?";

    private static final String SQL_EXCEPTION = "SQLException";

    /**
     *
     * @param routeStop
     */
    @Override
    public void create(RouteStop routeStop) {
        if (findAll().contains(routeStop)) {
            return;
        }
        JdbcDaoHelper.getInstance().executeQuery(ROUTE_STOP_CREATION_QUERY,
                new String[]{routeStop.getRouteId().toString(),
                    routeStop.getStopId().toString(),
                    routeStop.getNumber().toString()});

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public RouteStop find(int id) {

        RouteStop routeStop = null;

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement(FIND_ROUTE_STOP_BY_ID_QUERY)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                routeStop = new RouteStop(result.getInt(1), result.getInt(2),
                        result.getInt(3), result.getInt(4));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return routeStop;
    }

    /**
     *
     * @return
     */
    @Override
    public List<RouteStop> findAll() {
        List<RouteStop> routeStop = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (Statement statement = connection.createStatement()) {

            ResultSet result = statement
                    .executeQuery(FIND_ALL_ROUTE_STOP_QUERY);

            while (result.next()) {
                routeStop.add(new RouteStop(result.getInt(1), result.getInt(2),
                        result.getInt(3), result.getInt(4)));
            }
        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return routeStop;
    }

    /**
     *
     * @param routeStop
     */
    @Override
    public void update(RouteStop routeStop) {
        JdbcDaoHelper.getInstance().executeQuery(UPDATE_ROUTE_STOP_QUERY, new String[]{
            routeStop.getId().toString(),
            routeStop.getRouteId().toString(),
            routeStop.getStopId().toString(),
            routeStop.getNumber().toString()});
    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        JdbcDaoHelper.getInstance().excecuteDeleteQuery("route_stop", id);

    }

    /**
     *
     * @param route
     * @return
     */
    @Override
    public List<RouteStop> findByRoute(Route route) {
        List<RouteStop> routeStop = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement(FIND_ROUTE_STOP_BY_ROUTE_QUERY)) {

            statement.setInt(1, route.getId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                routeStop.add(new RouteStop(result.getInt(1), result.getInt(2),
                        result.getInt(3), result.getInt(4)));
            }
        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return routeStop;
    }

    /**
     *
     * @param routeId
     * @param stopId
     */
    @Override
    public void delete(int routeId, int stopId) {
        JdbcDaoHelper.getInstance().executeQuery(
                DELETE_ROUTE_STOP_BY_ROUTE_AND_STOP_QUERY,
                new String[]{Integer.toString(routeId),
                    Integer.toString(stopId)});
    }

}
