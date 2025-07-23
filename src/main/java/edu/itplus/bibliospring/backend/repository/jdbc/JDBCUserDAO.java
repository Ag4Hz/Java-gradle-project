package edu.itplus.bibliospring.backend.repository.jdbc;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.RepositoryException;
import edu.itplus.bibliospring.backend.repository.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("JDBC")
public class JDBCUserDAO implements UserDAO {
    @Autowired
    private Logger LOG;
    @Autowired
    private ConnectionManager connectionManager;

    @Override
    public User findById(Long id) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement prepstate = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            prepstate.setLong(1, id);
            ResultSet rs = prepstate.executeQuery();
            if (rs.next()) {
                User result = new User();
                result.setId(rs.getLong("id"));
                result.setUuid(rs.getString("uuid"));
                result.setUsername(rs.getString("username"));
                result.setPassword(rs.getString("password"));
                return result;
            }

            return null;
        } catch (SQLException e) {
            LOG.error("ERROR: findById: ", e);
            throw new RepositoryException("ERROR: findById: ", e);
        } finally {
            connectionManager.returnConnection(connection);
        }
    }

    @Override
    public User create(User user) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement prepstate = connection.prepareStatement("INSERT INTO users(uuid, username, password) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            prepstate.setString(1, user.getUuid());
            prepstate.setString(2, user.getUsername());
            prepstate.setString(3, user.getPassword());
            prepstate.executeUpdate();
            ResultSet rs = prepstate.getGeneratedKeys();
            rs.next();
            user.setId(rs.getLong(1));
            return user;
        } catch (SQLException e) {
            LOG.error("ERROR: create: ", e);
            throw new RepositoryException("ERROR: create: ", e);
        } finally {
            connectionManager.returnConnection(connection);
        }
    }

    @Override
    public void update(User user) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement prepstate = connection.prepareStatement("UPDATE users SET uuid = ?, username = ?, password = ? WHERE id = ?");
            prepstate.setString(1, user.getUuid());
            prepstate.setString(2, user.getUsername());
            prepstate.setString(3, user.getPassword());
            prepstate.setLong(4, user.getId());
            prepstate.executeUpdate();
        } catch (SQLException e) {
            LOG.error("ERROR: update: ", e);
            throw new RepositoryException("ERROR: update: ", e);
        } finally {
            connectionManager.returnConnection(connection);
        }
    }

    @Override
    public void delete(User user) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement prepstate = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            prepstate.setLong(1, user.getId());
            prepstate.executeUpdate();
        } catch (SQLException e) {
            LOG.error("ERROR: delete: ", e);
            throw new RepositoryException("ERROR: delete: ", e);
        } finally {
            connectionManager.returnConnection(connection);
        }
    }

    @Override
    public List<User> getAll() {
        Connection connection = null;
        List<User> result = new ArrayList<>();
        try {
            connection = connectionManager.getConnection();
            PreparedStatement prepstate = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = prepstate.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUuid(rs.getString("uuid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            LOG.error("ERROR: getAll: ", e);
            throw new RepositoryException("ERROR: getAll: ", e);
        } finally {
            connectionManager.returnConnection(connection);
        }
    }

    @Override
    public User findByUsername(String username) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement prepstate = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            prepstate.setString(1, username);
            ResultSet rs = prepstate.executeQuery();
            if (rs.next()) {
                User result = new User();
                result.setId(rs.getLong("id"));
                result.setUuid(rs.getString("uuid"));
                result.setUsername(rs.getString("username"));
                result.setPassword(rs.getString("password"));
                return result;
            }

            return null;
        } catch (SQLException e) {
            LOG.error("ERROR: findByUsername: ", e);
            throw new RepositoryException("ERROR: findByUsername: ", e);
        } finally {
            connectionManager.returnConnection(connection);
        }
    }
}
