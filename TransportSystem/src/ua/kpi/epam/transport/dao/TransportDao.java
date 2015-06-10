package ua.kpi.epam.transport.dao;

import java.util.List;

import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.entities.Transport;

/**
 *
 * @author KIRIL
 */
public interface TransportDao {

    /**
     *
     * @param transport
     */
    void create(Transport transport);

    /**
     *
     * @param id
     * @return
     */
    Transport find(int id);

    /**
     *
     * @return
     */
    List<Transport> findAll();

    /**
     *
     * @param transport
     */
    void update(Transport transport);

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
    List<Transport> findTransportOnRoute(Route route);
	
    /**
     *
     * @param schedule
     * @return
     */
    Transport findTransportOnSchedule(Schedule schedule);
}
