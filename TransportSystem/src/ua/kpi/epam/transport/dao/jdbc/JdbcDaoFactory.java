package ua.kpi.epam.transport.dao.jdbc;


import ua.kpi.epam.transport.dao.DaoFactory;

/**
 *
 * @author KIRIL
 */
public class JdbcDaoFactory extends DaoFactory {

    /**
     *
     * @return
     */
    @Override
	public JdbcUserDao createUserDao() {
		return new JdbcUserDao();
	}

    /**
     *
     * @return
     */
    @Override
	public JdbcTransportDao createTransportDao() {
		return new JdbcTransportDao();
	}

    /**
     *
     * @return
     */
    @Override
	public JdbcStopDao createStopDao() {
		return new JdbcStopDao();

	}

    /**
     *
     * @return
     */
    @Override
	public JdbcRouteDao createRouteDao() {
		return new JdbcRouteDao();

	}

    /**
     *
     * @return
     */
    @Override
	public JdbcRouteStopDao createRouteStopsDao() {
		return new JdbcRouteStopDao();

	}

    /**
     *
     * @return
     */
    @Override
	public JdbcScheduleOfStopDao createScheduleOfStopDao() {
		return new JdbcScheduleOfStopDao();

	}

    /**
     *
     * @return
     */
    @Override
	public JdbcScheduleDao createScheduleDao() {
		return new JdbcScheduleDao();

	}

	
}
