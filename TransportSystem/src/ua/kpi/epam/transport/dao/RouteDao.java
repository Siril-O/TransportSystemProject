package ua.kpi.epam.transport.dao;

import java.util.List;

import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Stop;

/**
 *
 * @author KIRIL
 */
public interface RouteDao {

    /**
     *
     * @param route
     */
    void create(Route route);

    /**
     *
     * @param id
     * @return
     */
    Route find(int id);

    /**
     *
     * @return
     */
    List<Route> findAll();

    /**
     *
     * @param route
     */
    void update(Route route);

    /**
     *
     * @param id
     */
    void delete(int id);

    /**
     *
     * @param startStop
     * @param finishStop
     * @return
     */
    List<Route> findRoutesByStops(Stop startStop, Stop finishStop);

}
