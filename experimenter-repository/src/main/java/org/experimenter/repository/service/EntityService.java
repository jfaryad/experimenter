package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.DeleteDependentException;
import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.form.CriteriaForm;

/**
 * Base interface for all entity services.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public interface EntityService<T extends Entity> {

    /**
     * Saves the given {@link Entity} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param entity
     *            the entity to save
     */
    public void saveUpdate(T entity);

    /**
     * Finds the {@link Entity} with the given id.
     * 
     * @param id
     *            the identifier of the entity
     * @return the entity with the given id or null, if such an entry doesn't exist in the database.
     */
    public T findById(Integer id);

    /**
     * Finds the {@link Entity} that matches the properties set in the entity form.
     * 
     * @param entity
     *            a search form with the properties you want to search by
     * @return a list of entitys that match the example
     */
    public List<T> findByExample(T entity);

    /**
     * Finds the {@link Entity} that matches the properties set in the entity form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of entitys that match the criteria
     */
    public List<T> findByCriteria(CriteriaForm<T> criteria);

    /**
     * Deletes the given {@link Entity} from the database, including all existing links to other tables.
     * 
     * @param entity
     *            the entity to delete
     */
    public void delete(T entity);

    /**
     * Deletes the given list of {@link Entity}s from the database, including all existing links to other tables.
     * 
     * @param entitys
     *            the entities to delete
     */
    public void delete(List<T> entities);

    /**
     * Tries to delete the given {@link Entity} from the database, but throws an {@link DeleteDependentException} if
     * there are any other entities dependent on it.
     * 
     * @param entity
     *            the entity to delete
     */
    public void tryDelete(T entity);
}
