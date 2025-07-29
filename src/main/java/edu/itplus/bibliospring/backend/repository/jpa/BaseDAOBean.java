package edu.itplus.bibliospring.backend.repository.jpa;

import edu.itplus.bibliospring.backend.model.AbstractModel;
import edu.itplus.bibliospring.backend.repository.BaseDAO;
import edu.itplus.bibliospring.backend.repository.RepositoryException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class BaseDAOBean<T extends AbstractModel, I> implements BaseDAO<T, I> {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private Logger LOG;

    private Class<T> entityType;

    public BaseDAOBean(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public T findById(I id) {
        try {
            return entityManager.find(entityType, id);
        } catch(PersistenceException e) {
            LOG.error("Error finding entity by ID: " + id, e);
            throw new RepositoryException("Error finding entity by ID: " + id, e);
        }
    }

    @Override
    public T create(T entity) {
        try {
            entityManager.persist(entity);
            entityManager.flush();
            return entity;
        } catch(PersistenceException e) {
            LOG.error("Error creating entity: " + entity, e);
            throw new RepositoryException("Error creating entity: " + entity, e);
        }
    }

    @Override
    public void update(T entity) {
        try {
            entityManager.merge(entity);
        } catch(PersistenceException e) {
            LOG.error("Error updating entity: " + entity, e);
            throw new RepositoryException("Error updating entity: " + entity, e);
        }
    }

    @Override
    public void delete(T entity) {
        try {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        } catch(PersistenceException e) {
            LOG.error("Error deleting entity: " + entity, e);
            throw new RepositoryException("Error deleting entity: " + entity, e);
        }
    }

    @Override
    public List<T> getAll() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder ();
            CriteriaQuery<T> criteriaQuery = cb.createQuery (entityType);
            Root<T> rootEntry = criteriaQuery.from (entityType);
            CriteriaQuery<T> all = criteriaQuery.select (rootEntry);
            TypedQuery<T> allQuery = entityManager.createQuery (all);
            return allQuery.getResultList ();
        } catch(PersistenceException e) {
            LOG.error("Error retrieving all entities of type: " + entityType.getSimpleName(), e);
            throw new RepositoryException("Error retrieving all entities of type: " + entityType.getSimpleName(), e);
        }
    }
}
