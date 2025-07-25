package edu.itplus.bibliospring.backend.services.impl;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserDAO;

import java.util.List;

public class TestUserDAO implements UserDAO {
    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
