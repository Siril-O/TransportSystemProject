package ua.kpi.epam.transport.dao;

import java.util.List;

import ua.kpi.epam.transport.entities.User;

/**
 *
 * @author KIRIL
 */
public interface UserDao {

    /**
     *
     * @param user
     */
    void create(User user);

    /**
     *
     * @param login
     * @return
     */
    User findByLogin(String login);

    /**
     *
     * @param id
     * @return
     */
    User find(int id);

    /**
     *
     * @param login
     * @param passwordHash
     * @return
     */
    User autentify(String login, int passwordHash);

    /**
     *
     * @return
     */
    List<User> findAll();

    /**
     *
     * @param user
     */
    void update(User user);

    /**
     *
     * @param id
     */
    void delete(int id);

}
