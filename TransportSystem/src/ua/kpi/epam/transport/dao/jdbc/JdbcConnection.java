package ua.kpi.epam.transport.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import ua.kpi.epam.transport.extras.LoggerHelper;

/**
 *
 * @author KIRIL
 */
public class JdbcConnection {

    private static final String CONNECTIOM_POOL_RESOURCE_NAME = "jdbc/Transport";
    private static final String NAMING_EXCEPTION = "NamingException";
    private static final String CONNECTION_ERROR = "ConnectionError";



    public JdbcConnection() {
        try {
            connection = createConnection();
        } catch (SQLException ex) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(CONNECTION_ERROR), ex);
        }
    }

    /**
     *
     * @return
     */
    public static JdbcConnection getInstance() {
        return new JdbcConnection();
    }

    private Connection connection;

    /**
     *
     * @return
     */
    public Connection getConnection() {
        try {
            if (!connection.isValid(1)) {
                connection.close();
                connection = createConnection();
            }
        } catch (SQLException ex) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(CONNECTION_ERROR), ex);
        }
        return connection;
    }

    private Connection createConnection() throws SQLException {
        InitialContext initContext;
        Connection conn = null;
        try {
            initContext = new InitialContext();

            DataSource ds = (DataSource) initContext.lookup(CONNECTIOM_POOL_RESOURCE_NAME);
            conn = ds.getConnection();
        } catch (NamingException ex) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(NAMING_EXCEPTION), ex);
        }
        return conn;
//		return DriverManager.getConnection(
//				"jdbc:mysql://localhost:3306/transport", "root", "");
    }

}
