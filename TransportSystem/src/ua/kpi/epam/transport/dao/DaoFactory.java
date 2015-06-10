package ua.kpi.epam.transport.dao;


import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KIRIL
 */
public abstract class DaoFactory {

    private static final String DAO_FACTORY_NAME = "DaoFactory";
    private static final String PROPERTIES_PATH = "/ua/kpi/epam/transport/properties/dao.properties";

    /**
     *
     * @return
     */
    public abstract UserDao createUserDao();

    /**
     *
     * @return
     */
    public abstract TransportDao createTransportDao();

    /**
     *
     * @return
     */
    public abstract StopDao createStopDao();

    /**
     *
     * @return
     */
    public abstract RouteDao createRouteDao();

    /**
     *
     * @return
     */
    public abstract RouteStopDao createRouteStopsDao();

    /**
     *
     * @return
     */
    public abstract ScheduleOfStopDao createScheduleOfStopDao();

    /**
     *
     * @return
     */
    public abstract ScheduleDao createScheduleDao();

    /**
     *
     * @return
     */
    public static DaoFactory getInstance() {

        Properties config = new Properties();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            config.load(classLoader.getResourceAsStream(PROPERTIES_PATH));

            return (DaoFactory) Class.forName(config.getProperty(DAO_FACTORY_NAME)).newInstance();

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }

}
