package ua.kpi.epam.transport.dao;

import java.util.List;

import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.RouteStop;

/**
 *
 * @author KIRIL
 */
public interface RouteStopDao {

    /**
     *
     * @param routeStop
     */
    void create(RouteStop routeStop);

    /**
     *
     * @param id
     * @return
     */
    RouteStop find(int id);

    /**
     *
     * @return
     */
    List<RouteStop> findAll();

    /**
     *
     * @param routeStop
     */
    void update(RouteStop routeStop);

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
    List<RouteStop> findByRoute(Route route);
	
    /**
     *
     * @param routeId
     * @param stopId
     */
    void delete( int routeId, int stopId);
}
