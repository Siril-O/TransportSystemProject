package ua.kpi.epam.transport.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

import ua.kpi.epam.transport.dao.StopDao;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Stop;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import ua.kpi.epam.transport.extras.LoggerHelper;

/**
 *
 * @author KIRIL
 */
public class JdbcStopDao implements StopDao {

    private static final String UPDATE_STOP_QUERY = "UPDATE stop SET name=?, address=?  WHERE id=?";
    private static final String CREATE_STOP_QUERY = "INSERT INTO stop (name, address)  VALUES (?, ?)";
    private static final String FIND_ALL_STOPS_ON_ROOT = "SELECT  s.id, s.name, s.address FROM route r , route_stop rs  "
            + ", stop s WHERE r.id=rs.route_id AND s.id=rs.stop_id AND r.id=? ORDER BY rs.number ASC";

    private static final String SQL_EXCEPTION = "SQLException";

    /**
     *
     * @param stop
     */
    @Override
    public void create(Stop stop) {
        JdbcDaoHelper.getInstance().executeQuery(CREATE_STOP_QUERY,
                new String[]{stop.getName(), stop.getAddress()});
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Stop find(int id) {

        Stop stop = null;
        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM stop WHERE id=?")) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                stop = new Stop(result.getInt(1), result.getString(2),
                        result.getString(3));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return stop;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Stop> findAll() {
        List<Stop> stops = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (Statement query = connection.createStatement()) {

            ResultSet result = query.executeQuery("SELECT * FROM stop");

            while (result.next()) {

                stops.add(new Stop(result.getInt(1), result.getString(2),
                        result.getString(3)));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return stops;
    }

    /**
     *
     * @param stop
     */
    @Override
    public void update(Stop stop) {

        JdbcDaoHelper.getInstance().executeQuery(UPDATE_STOP_QUERY,
                new String[]{stop.getName(), stop.getAddress(),
                    stop.getId().toString()});
    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(int id) {

        JdbcDaoHelper.getInstance().excecuteDeleteQuery("stop", id);
    }

    /**
     *
     * @param route
     * @return
     */
    @Override
    public List<Stop> findAllStopsOnRoute(Route route) {

        List<Stop> stops = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement query = connection
                .prepareStatement(FIND_ALL_STOPS_ON_ROOT)) {

            query.setInt(1, route.getId());

            ResultSet result = query.executeQuery();

            while (result.next()) {

                stops.add(new Stop(result.getInt(1), result.getString(2),
                        result.getString(3)));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return stops;

    }

}
