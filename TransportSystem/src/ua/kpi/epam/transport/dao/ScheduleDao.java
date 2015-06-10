package ua.kpi.epam.transport.dao;

import java.util.List;

import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Schedule;

/**
 *
 * @author KIRIL
 */
public interface ScheduleDao {

    /**
     *
     * @param schedule
     */
    void create(Schedule schedule);
        
    /**
     *
     * @param schedule
     * @return
     */
    int createAndGetGenratedKey(Schedule schedule);

    /**
     *
     * @param id
     * @return
     */
    Schedule find(int id);

    /**
     *
     * @return
     */
    List<Schedule> findAll();

    /**
     *
     * @param schedule
     */
    void update(Schedule schedule);

    /**
     *
     * @param schedule
     */
    void updateTransport(Schedule schedule);
        
    /**
     *
     * @param id
     */
    void delete(int id);

    /**
     *
     * @param id
     */
    void deleteFully(int id);
	
    /**
     *
     * @param route
     * @return
     */
    List<Schedule> findSchedulesForRoute(Route route);
	
}
