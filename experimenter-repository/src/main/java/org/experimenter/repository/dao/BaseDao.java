package org.experimenter.repository.dao;

public interface BaseDao<T> {
    public T findById(Integer id);

    public void insert(T item);
}
