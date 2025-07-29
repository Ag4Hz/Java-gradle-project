package edu.itplus.bibliospring.backend.repository;

import edu.itplus.bibliospring.backend.model.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User, Long> {
    User findByUsername(String username);

}
