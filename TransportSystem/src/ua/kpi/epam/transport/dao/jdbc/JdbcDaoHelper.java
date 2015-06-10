package ua.kpi.epam.transport.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import ua.kpi.epam.transport.extras.LoggerHelper;

/**
 *
 * @author KIRIL
 */
public class JdbcDaoHelper {

    private static volatile JdbcDaoHelper instance;
    private static final String SQL_EXCEPTION = "SQLException";
    private static final String WHERE_ID = " WHERE id=?";
    private static final String DELETE_FROM = "DELETE FROM ";

    private JdbcDaoHelper() {
    }

    /**
     *
     * @return
     */
    public static JdbcDaoHelper getInstance() {
        if (instance == null) {
            synchronized (JdbcDaoHelper.class) {
                if (instance == null) {
                    instance = new JdbcDaoHelper();
                }
            }
        }
        return instance;
    }

    /**
     *
     * @param query
     * @param params
     */
    public void executeQuery(String query, String[] params) {
        Connection connection = JdbcConnection.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    /**
     *
     * @param table
     * @param id
     */
    public void excecuteDeleteQuery(String table, int id) {

        Connection connection = JdbcConnection.getInstance().getConnection();

        String query = DELETE_FROM + table + WHERE_ID;
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

}
