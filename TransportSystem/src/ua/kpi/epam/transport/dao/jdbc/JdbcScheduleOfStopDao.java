package ua.kpi.epam.transport.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

import ua.kpi.epam.transport.dao.ScheduleOfStopDao;
import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.entities.ScheduleOfStop;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import ua.kpi.epam.transport.extras.LoggerHelper;

/**
 *
 * @author KIRIL
 */
public class JdbcScheduleOfStopDao implements ScheduleOfStopDao {

    private static final String FIND_SCHEDULE_OF_STOP_FOR_SCHEDULE_QUERY = "SELECT  ss.* FROM schedule_of_stops ss, schedule s, route_stop rs  WHERE s.id=? AND s.id=ss.schedule_id AND s.route_id=rs.route_id AND ss.stop_id=rs.stop_id ORDER BY rs.number ASC ";
    private static final String DELETE_SCHEDULE_OF_STOPS_QUERY = "DELETE FROM schedule_of_stops WHERE schedule_id=?";
    private static final String SCHEDULE_OF_STOP_CREATION_QUERY = "INSERT INTO schedule_of_stops (stop_id, schedule_id, arrive_time, leave_time)  VALUES (?,?,?,?)";
    private static final String FIND_ALL_SCHEDULE_OF_STOP_QUERY = "SELECT * FROM schedule_of_stops";
    private static final String SCHEDULE_OF_STOP_UPDATE_QUERY = "UPDATE schedule_of_stops SET stop_id=?, schedule_id=?, arrive_time=?, leave_time=?  WHERE id=?";
    private static final String FIND_SCHEDULE_OF_STOP_BY_ID_QUERY = "SELECT * FROM schedule_of_stops WHERE id=?";
    private static final String FIND_SCHEDULE_OF_STOP_BY_SCHEDULE_AND_STOP_QUERY = "SELECT * FROM schedule_of_stops WHERE schedule_id=? AND stop_id=? ";
    private static final String TABLE_NAME = "schedule_of_stops";

    private static final String SQL_EXCEPTION = "SQLException";

    /**
     *
     * @param scheduleOfStop
     */
    @Override
    public void create(ScheduleOfStop scheduleOfStop) {
        if (findAll().contains(scheduleOfStop)) {
            return;
        }
        JdbcDaoHelper.getInstance().executeQuery(
                SCHEDULE_OF_STOP_CREATION_QUERY,
                new String[]{scheduleOfStop.getStopId().toString(),
                    scheduleOfStop.getScheduleId().toString(),
                    scheduleOfStop.getArriveTime(),
                    scheduleOfStop.getLeaveTime()});
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ScheduleOfStop find(int id) {
        ScheduleOfStop scheduleOfStop = null;

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement(FIND_SCHEDULE_OF_STOP_BY_ID_QUERY)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                scheduleOfStop = new ScheduleOfStop(result.getInt(1),
                        result.getInt(2), result.getInt(3),
                        result.getString(4), result.getString(5));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return scheduleOfStop;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ScheduleOfStop> findAll() {
        List<ScheduleOfStop> scheduleOfStop = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (Statement query = connection.createStatement()) {

            ResultSet result = query
                    .executeQuery(FIND_ALL_SCHEDULE_OF_STOP_QUERY);

            while (result.next()) {
                scheduleOfStop.add(new ScheduleOfStop(result.getInt(1), result
                        .getInt(2), result.getInt(3), result.getString(4),
                        result.getString(5)));
            }
        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return scheduleOfStop;
    }

    /**
     *
     * @param scheduleOfStop
     */
    @Override
    public void update(ScheduleOfStop scheduleOfStop) {
        JdbcDaoHelper.getInstance().executeQuery(
                SCHEDULE_OF_STOP_UPDATE_QUERY,
                new String[]{scheduleOfStop.getStopId().toString(),
                    scheduleOfStop.getScheduleId().toString(),
                    scheduleOfStop.getArriveTime(),
                    scheduleOfStop.getLeaveTime(),
                    scheduleOfStop.getId().toString()});

    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        JdbcDaoHelper.getInstance()
                .excecuteDeleteQuery(TABLE_NAME, id);

    }

    /**
     *
     * @param schedule
     * @return
     */
    @Override
    public List<ScheduleOfStop> findScheduleOfStopsForSchedule(Schedule schedule) {

        List<ScheduleOfStop> scheduleOfStops = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement query = connection
                .prepareStatement(FIND_SCHEDULE_OF_STOP_FOR_SCHEDULE_QUERY)) {

            query.setInt(1, schedule.getId());

            ResultSet result = query.executeQuery();

            while (result.next()) {

                scheduleOfStops.add(new ScheduleOfStop(result.getInt(1), result
                        .getInt(2), result.getInt(3), result.getString(4),
                        result.getString(5)));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return scheduleOfStops;
    }

    /**
     *
     * @param scheduleId
     */
    @Override
    public void deleteBySchedule(int scheduleId) {

        JdbcDaoHelper.getInstance().executeQuery(DELETE_SCHEDULE_OF_STOPS_QUERY,
                new String[]{Integer.toString(scheduleId)});
    }

    /**
     *
     * @param scheduleId
     * @param stopId
     * @return
     */
    @Override
    public ScheduleOfStop find(int scheduleId, int stopId) {
        ScheduleOfStop scheduleOfStop = null;

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement(FIND_SCHEDULE_OF_STOP_BY_SCHEDULE_AND_STOP_QUERY)) {

            statement.setInt(1, scheduleId);
            statement.setInt(2, stopId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                scheduleOfStop = new ScheduleOfStop(result.getInt(1),
                        result.getInt(2), result.getInt(3),
                        result.getString(4), result.getString(5));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return scheduleOfStop;
    }

}
