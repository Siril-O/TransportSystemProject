package ua.kpi.epam.transport.dao;

import java.util.List;


import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.entities.ScheduleOfStop;

/**
 *
 * @author KIRIL
 */
public interface ScheduleOfStopDao {

    /**
     *
     * @param scheduleOfStop
     */
    void create(ScheduleOfStop scheduleOfStop);

    /**
     *
     * @param id
     * @return
     */
    ScheduleOfStop find(int id);

    /**
     *
     * @param scheduleId
     * @param stopId
     * @return
     */
    ScheduleOfStop find(int scheduleId, int stopId);
        
    /**
     *
     * @return
     */
    List<ScheduleOfStop> findAll();

    /**
     *
     * @param scheduleOfStop
     */
    void update(ScheduleOfStop scheduleOfStop);

    /**
     *
     * @param id
     */
    void delete(int id);
	
    /**
     *
     * @param scheduleId
     */
    void deleteBySchedule(int scheduleId);

    /**
     *
     * @param schedule
     * @return
     */
    List<ScheduleOfStop> findScheduleOfStopsForSchedule(Schedule schedule);
}
