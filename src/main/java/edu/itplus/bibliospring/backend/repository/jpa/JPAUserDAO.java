package edu.itplus.bibliospring.backend.repository.jpa;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.RepositoryException;
import edu.itplus.bibliospring.backend.repository.UserDAO;
import jakarta.persistence.PersistenceException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("JPA")
public class JPAUserDAO extends BaseDAOBean<User, Long> implements UserDAO {
    @Autowired
    private Logger LOG;

    public JPAUserDAO() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (PersistenceException e) {
            LOG.error("Error finding user by username: " + username, e);
            throw new RepositoryException("Error finding user by username: " + username, e);
        }
    }
}
