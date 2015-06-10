package ua.kpi.epam.transport.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

import ua.kpi.epam.transport.dao.ScheduleDao;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import ua.kpi.epam.transport.extras.LoggerHelper;

/**
 *
 * @author KIRIL
 */
public class JdbcScheduleDao implements ScheduleDao {

    private static final String SCHEDULE_TABLE_NAME = "schedule";
    private static final String SELECT_ALL_SCHEDULE_QUERY = "SELECT * FROM schedule";
    private static final String UPDATE_SCHEDULE_QUERY = "UPDATE schedule SET route_id=?, transport_id=?  WHERE id=?";
    private static final String UPDATE_SCHEDULE_TRANSPORT_QUERY = "UPDATE schedule SET transport_id=?  WHERE id=?";
    private static final String FIND_SCHEDULE_BY_ID_QUERY = "SELECT * FROM schedule WHERE id=?";
    private static final String SCHEDULE_CREATION_QUERY = "INSERT INTO schedule (route_id , transport_id)  VALUES (?, ?)";
    private static final String FIND_SCHEDULES_FOR_ROUTE_QUERY = "SELECT * FROM schedule WHERE route_id=?;";
    private static final String DELETE_SCHEDULE_OF_STOPS_QUERY = "DELETE   FROM  schedule_of_stops  WHERE  schedule_id=?";

    private static final String SQL_EXCEPTION = "SQLException";

    /**
     *
     * @param schedule
     */
    @Override
    public void create(Schedule schedule) {

        JdbcDaoHelper.getInstance().executeQuery(SCHEDULE_CREATION_QUERY, new String[]{
            schedule.getRouteId().toString(),
            schedule.getTransportId() == null ? null : schedule.getTransportId().toString()});

    }

    /**
     *
     * @param schedule
     * @return
     */
    @Override
    public int createAndGetGenratedKey(Schedule schedule) {

        Connection connection = JdbcConnection.getInstance().getConnection();

        Integer key = null;

        try (PreparedStatement statement = connection.prepareStatement(SCHEDULE_CREATION_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, schedule.getRouteId());
            statement.setInt(2, schedule.getTransportId());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();

            while (result.next()) {
                key = result.getInt(1);
            }
        } catch (SQLException ex) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), ex);
        }
        return key;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Schedule find(int id) {
        Schedule schedule = null;

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement(FIND_SCHEDULE_BY_ID_QUERY)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                schedule = new Schedule(result.getInt(1), result.getInt(2),
                        result.getInt(3));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return schedule;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Schedule> findAll() {
        List<Schedule> schedule = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (Statement query = connection.createStatement()) {

            ResultSet result = query.executeQuery(SELECT_ALL_SCHEDULE_QUERY);

            while (result.next()) {
                schedule.add(new Schedule(result.getInt(1), result.getInt(2),
                        result.getInt(3)));
            }
        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return schedule;
    }

    /**
     *
     * @param schedule
     */
    @Override
    public void update(Schedule schedule) {
        JdbcDaoHelper.getInstance().executeQuery(UPDATE_SCHEDULE_QUERY, new String[]{
            schedule.getRouteId().toString(),
            schedule.getTransportId().toString(),
            schedule.getId().toString()});
    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        JdbcDaoHelper.getInstance().excecuteDeleteQuery(SCHEDULE_TABLE_NAME, id);

    }

    /**
     *
     * @param route
     * @return
     */
    @Override
    public List<Schedule> findSchedulesForRoute(Route route) {

        List<Schedule> scheduleList = new LinkedList<>();
        Connection conection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = conection
                .prepareStatement(FIND_SCHEDULES_FOR_ROUTE_QUERY)) {

            statement.setInt(1, route.getId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                scheduleList.add(new Schedule(result.getInt(1), result
                        .getInt(2), result.getInt(3)));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return scheduleList;
    }

    /**
     *
     * @param id
     */
    @Override
    public void deleteFully(int id) {

        JdbcDaoHelper.getInstance().executeQuery(DELETE_SCHEDULE_OF_STOPS_QUERY,
                new String[]{Integer.toString(id)});
        delete(id);
    }

    /**
     *
     * @param schedule
     */
    @Override
    public void updateTransport(Schedule schedule) {
        JdbcDaoHelper.getInstance().executeQuery(UPDATE_SCHEDULE_TRANSPORT_QUERY, new String[]{
            schedule.getTransportId().toString(),
            schedule.getId().toString()});
    }

}
