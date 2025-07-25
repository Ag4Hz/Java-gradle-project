package edu.itplus.bibliospring.backend.services.impl;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserDAO;

import java.util.List;

public class TestUserDAO implements UserDAO {
    public static User nonDbUser;

    public static User dbUser;

    private String lastSearchedUsernName;
    public String getLastSearchedUsernName(){
        return lastSearchedUsernName;
    }

    public TestUserDAO() {
        nonDbUser = new User();
        nonDbUser.setPassword(TestPasswordEncrypter.password);
        nonDbUser.setUsername("Pistike");
        nonDbUser.setUuid(TestPasswordEncrypter.salt);
        nonDbUser.setId(1L);

        dbUser = new User();
        dbUser.setPassword(TestPasswordEncrypter.hashedPassword);
        dbUser.setUsername("Pistike");
        dbUser.setUuid(TestPasswordEncrypter.salt);
        dbUser.setId(1L);
    }

    @Override
    public User findById(Long id) {
        if (nonDbUser.getId().equals(id)) {
            return dbUser;
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
        return List.of(new User[]{dbUser});
    }

    @Override
    public User findByUsername(String username) {
        lastSearchedUsernName = username;
        if (nonDbUser.getUsername().equals(username)) {
            return dbUser;
        } else {
            return null;
        }
    }
}
