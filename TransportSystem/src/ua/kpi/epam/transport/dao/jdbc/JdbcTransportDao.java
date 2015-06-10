package ua.kpi.epam.transport.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

import ua.kpi.epam.transport.dao.TransportDao;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.entities.Transport;
import ua.kpi.epam.transport.entities.TransportFactory;
import ua.kpi.epam.transport.entities.enums.TransportType;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import ua.kpi.epam.transport.extras.LoggerHelper;

/**
 *
 * @author KIRIL
 */
public class JdbcTransportDao implements TransportDao {

    private static final String TRANSPORT_TABLE_NAME = "transport";
    private static final String UPDATE_TRANSPORT_QUERY = "UPDATE transport SET type=?, number=?, model=? WHERE id=?";
    private static final String FIND_TRANSPORT_BY_ID_QUERY = "SELECT * FROM transport WHERE id=?";
    private static final String TRANSPOR_CREATION_QUERY = "INSERT INTO transport (type , number, model)  VALUES (?, ?, ?)";
    private static final String FIND_TRANSPORT_ON_ROOT_QUERY = "SELECT t.id, t.type, t.number, t.model FROM route r, schedule sc,transport t WHERE r.id=? AND r.id=sc.route_id AND sc.transport_id=t.id";
    private static final String FIND_TRANSPORT_ON_SCHEDULE_QUERY = "SELECT t.id, t.type, t.number, t.model FROM schedule sc, transport t WHERE sc.id=? AND sc.transport_id=t.id";

    private static final String SQL_EXCEPTION = "SQLException";

    /**
     *
     * @param transport
     */
    @Override
    public void create(Transport transport) {

        if (findAll().contains(transport)) {
            return;
        }
        JdbcDaoHelper.getInstance().executeQuery(TRANSPOR_CREATION_QUERY, new String[]{
            transport.getType().getName(),
            transport.getNumber().toString(), transport.getModel()});
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Transport find(int id) {

        Transport transport = null;

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement(FIND_TRANSPORT_BY_ID_QUERY)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                TransportFactory factory = TransportFactory.getInstance();
                transport = factory.getTransport(result.getInt(1),
                        TransportType
                        .valueOf(result.getString(2).toUpperCase()),
                        result.getInt(3), result.getString(4));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return transport;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Transport> findAll() {
        List<Transport> transport = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (Statement query = connection.createStatement()) {

            ResultSet result = query.executeQuery("SELECT * FROM transport");

            while (result.next()) {
                TransportFactory factory = TransportFactory.getInstance();
                transport.add(factory.getTransport(result.getInt(1),
                        TransportType
                        .valueOf(result.getString(2).toUpperCase()),
                        result.getInt(3), result.getString(4)));
            }
        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return transport;
    }

    /**
     *
     * @param transport
     */
    @Override
    public void update(Transport transport) {

        JdbcDaoHelper.getInstance().executeQuery(UPDATE_TRANSPORT_QUERY, new String[]{
            transport.getType().getName(),
            transport.getNumber().toString(), transport.getModel(),
            transport.getId().toString()});
    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        JdbcDaoHelper.getInstance().excecuteDeleteQuery(TRANSPORT_TABLE_NAME, id);
    }

    /**
     *
     * @param route
     * @return
     */
    @Override
    public List<Transport> findTransportOnRoute(Route route) {
        List<Transport> transport = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement query = connection
                .prepareStatement(FIND_TRANSPORT_ON_ROOT_QUERY)) {

            query.setInt(1, route.getId());
            ResultSet result = query.executeQuery();

            while (result.next()) {
                TransportFactory factory = TransportFactory.getInstance();
                transport.add(factory.getTransport(result.getInt(1),
                        TransportType
                        .valueOf(result.getString(2).toUpperCase()),
                        result.getInt(3), result.getString(4)));
            }
        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return transport;
    }

    /**
     *
     * @param schedule
     * @return
     */
    @Override
    public Transport findTransportOnSchedule(Schedule schedule) {

        Transport transport = null;
        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement query = connection
                .prepareStatement(FIND_TRANSPORT_ON_SCHEDULE_QUERY)) {

            query.setInt(1, schedule.getId());
            ResultSet result = query.executeQuery();

            while (result.next()) {
                TransportFactory factory = TransportFactory.getInstance();
                transport = factory.getTransport(result.getInt(1),
                        TransportType
                        .valueOf(result.getString(2).toUpperCase()),
                        result.getInt(3), result.getString(4));
            }
        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return transport;
    }

}
