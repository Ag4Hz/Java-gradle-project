package edu.itplus.bibliospring.backend.services.impl;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserDAO;

import java.util.List;

public class TestUserDAO implements UserDAO {
    private User user;

    public TestUserDAO() {
        user = new User();
        user.setPassword("123");
        user.setUsername("Pistike");
        user.setUuid("salt");
        user.setId(1L);
    }

    @Override
    public User findById(Long id) {
        if (user.getId().equals(id)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Not implemented in TestUserDAO");
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException("Not implemented in TestUserDAO");
    }

    @Override
    public List<User> getAll() {
        return List.of(new User[]{user});
    }

    @Override
    public User findByUsername(String username) {
        if (user.getUsername().equals(username)) {
            return user;
        } else {
            return null;
        }
    }
}
