package edu.itplus.bibliospring.backend.repository.jpa;

import edu.itplus.bibliospring.backend.model.AbstractModel;
import edu.itplus.bibliospring.backend.repository.BaseDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class BaseDAOBean<T extends AbstractModel, I> implements BaseDAO<T, I> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityType;

    public BaseDAOBean(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public T findById(I id) {
        return entityManager.find(entityType, id);
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public List<T> getAll() {
        return List.of();
    }
}
