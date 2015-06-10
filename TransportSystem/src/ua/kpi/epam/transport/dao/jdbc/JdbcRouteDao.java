package ua.kpi.epam.transport.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

import ua.kpi.epam.transport.dao.RouteDao;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Stop;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import ua.kpi.epam.transport.extras.LoggerHelper;

/**
 *
 * @author KIRIL
 */
public class JdbcRouteDao implements RouteDao {

    private static final String ROUTE_TABLE_NAME = "route";
    private static final String FIND_ALL_ROUTES_QUERY = "SELECT * FROM route";
    private static final String UPDATE_ROUTE_QUERY = "UPDATE route SET name=?  WHERE id=?";
    private static final String FIND_ROUTE_BY_ID_QUERY = "SELECT * FROM route WHERE id=?";
    private static final String ROUTE_CREATION_QUERY = "INSERT INTO route (name)  VALUES (?)";
    private static final String FIND_ROUTES_BY_STOPS_QUERY = "SELECT DISTINCT r.id, r.name FROM route r, route_stop rs, stop s WHERE r.id=rs.route_id AND rs.stop_id=s.id AND (s.name=? OR  s.name=? )";

    private static final String SQL_EXCEPTION = "SQLException";

    /**
     *
     * @param route
     */
    @Override
    public void create(Route route) {

        if (findAll().contains(route)) {
            return;
        }
        JdbcDaoHelper.getInstance().executeQuery(ROUTE_CREATION_QUERY,
                new String[]{route.getName()});
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Route find(int id) {

        Route route = null;

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement(FIND_ROUTE_BY_ID_QUERY)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                route = new Route(result.getInt(1), result.getString(2));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return route;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Route> findAll() {
        List<Route> route = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (Statement query = connection.createStatement()) {

            ResultSet result = query.executeQuery(FIND_ALL_ROUTES_QUERY);

            while (result.next()) {
                route.add(new Route(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return route;
    }

    /**
     *
     * @param route
     */
    @Override
    public void update(Route route) {
        JdbcDaoHelper.getInstance().executeQuery(UPDATE_ROUTE_QUERY, new String[]{
            route.getName(), route.getId().toString()});

    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        JdbcDaoHelper.getInstance().excecuteDeleteQuery(ROUTE_TABLE_NAME, id);
    }

    /**
     *
     * @param startStop
     * @param finishStop
     * @return
     */
    @Override
    public List<Route> findRoutesByStops(Stop startStop, Stop finishStop) {
        List<Route> route = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement query = connection
                .prepareStatement(FIND_ROUTES_BY_STOPS_QUERY)) {

            query.setString(1, startStop.getAddress());
//            query.setString(3, startStop.getAddress());
            query.setString(2, finishStop.getAddress());
//            query.setString(4, finishStop.getAddress());

            ResultSet result = query.executeQuery();

            while (result.next()) {
                route.add(new Route(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return route;
    }

}
