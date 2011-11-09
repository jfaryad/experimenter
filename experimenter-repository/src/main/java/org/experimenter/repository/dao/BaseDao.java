package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.model.Model;

public interface BaseDao<T extends Model> {

    public T findById(Integer id);

    public List<T> findByCriteria(CriteriaForm<T> criteria);

    public void insert(T item);

    public void deleteById(Integer id);

    public void update(T item);

    public Class<T> getModelClass();
}
