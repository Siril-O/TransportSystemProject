package ua.kpi.epam.transport.dao;

import java.util.List;

import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Stop;

/**
 *
 * @author KIRIL
 */
public interface StopDao {

    /**
     *
     * @param stop
     */
    void create(Stop stop);

    /**
     *
     * @param id
     * @return
     */
    Stop find(int id);

    /**
     *
     * @return
     */
    List<Stop> findAll();

    /**
     *
     * @param stop
     */
    void update(Stop stop);

    /**
     *
     * @param id
     */
    void delete(int id);

    /**
     *
     * @param route
     * @return
     */
    List<Stop> findAllStopsOnRoute(Route route);
}
