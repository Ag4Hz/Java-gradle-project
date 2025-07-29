package edu.itplus.bibliospring.backend.repository;

import edu.itplus.bibliospring.backend.model.AbstractModel;
import java.util.List;

public interface BaseDAO<T extends AbstractModel, I> {
    T findById(I id);
    T create(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> getAll();
}
