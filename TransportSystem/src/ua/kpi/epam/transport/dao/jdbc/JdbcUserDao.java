package ua.kpi.epam.transport.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

import ua.kpi.epam.transport.dao.UserDao;
import ua.kpi.epam.transport.entities.User;
import ua.kpi.epam.transport.entities.enums.UserRole;
import ua.kpi.epam.transport.extras.LocalizationHelper;
import ua.kpi.epam.transport.extras.LoggerHelper;

/**
 *
 * @author KIRIL
 */
public class JdbcUserDao implements UserDao {

    private static final String USER_TABLE_NAME = "user";
    private static final String FIND_USER_BY_LOGIN_PASSWORD_HASH_QUERY = "SELECT * FROM user WHERE login=?  AND  password_hash=? ";
    private static final String FIND_USER_BY_LOGIN_QUERY = "SELECT * FROM user WHERE login=?";
    private static final String UPDATE_USER_QUERY = "UPDATE user SET login=?, name=?, surname=?, password_hash=?, role=? WHERE id=?";
    private static final String CREATE_USER_QUERY = "INSERT INTO user (login , name, surname, password_hash, role)  VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_EXCEPTION = "SQLException";

    /**
     *
     * @param user
     */
    @Override
    public void create(User user) {

        JdbcDaoHelper.getInstance().executeQuery(
                CREATE_USER_QUERY,
                new String[]{user.getLogin(), user.getName(),
                    user.getSurname(), user.getPasswordHash().toString(),
                    user.getRole().getRole()});
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public User find(int id) {

        User user = null;
        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM user WHERE id=?")) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getInt(5), UserRole.valueOf(result.getString(6)
                                .toUpperCase()));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return user;
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> findAll() {

        List<User> users = new LinkedList<>();

        Connection connection = JdbcConnection.getInstance().getConnection();

        try (Statement query = connection.createStatement()) {

            ResultSet rs = query.executeQuery("SELECT * FROM user");

            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs
                        .getString(3), rs.getString(4), rs.getInt(5), UserRole
                        .valueOf(rs.getString(6).toUpperCase())));
            }

            return users;
        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return users;
    }

    /**
     *
     * @param user
     */
    @Override
    public void update(User user) {

        JdbcDaoHelper.getInstance().executeQuery(
                UPDATE_USER_QUERY,
                new String[]{user.getLogin(), user.getName(),
                    user.getSurname(), user.getPasswordHash().toString(),
                    user.getRole().getRole(), user.getId().toString()});

    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(int id) {

        JdbcDaoHelper.getInstance().excecuteDeleteQuery(USER_TABLE_NAME, id);
    }

    /**
     *
     * @param login
     * @param passwordHash
     * @return
     */
    @Override
    public User autentify(String login, int passwordHash) {
        User user = null;
        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement(FIND_USER_BY_LOGIN_PASSWORD_HASH_QUERY)) {

            statement.setString(1, login);
            statement.setInt(2, passwordHash);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getInt(5), UserRole.valueOf(result.getString(6)
                                .toUpperCase()));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return user;
    }

    /**
     *
     * @param login
     * @return
     */
    @Override
    public User findByLogin(String login) {
        User user = null;
        Connection connection = JdbcConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement(FIND_USER_BY_LOGIN_QUERY)) {

            statement.setString(1, login);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getInt(5), UserRole.valueOf(result.getString(6)
                                .toUpperCase()));
            }

        } catch (SQLException e) {
            Logger logger = (Logger) LoggerHelper.getInstance().getLogger();
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return user;
    }

}
