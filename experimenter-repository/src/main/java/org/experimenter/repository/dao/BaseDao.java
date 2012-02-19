package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.form.CriteriaForm;

public interface BaseDao<T extends Entity> {

    /**
     * Retrieves the entity by it's id.
     * 
     * @param id
     *            the id of the entity
     * @return the entity with the given id or null, if it doesn't exist
     */
    public T findById(Integer id);

    /**
     * Retrieves the entities matching the given criteria.
     * 
     * @param criteria
     *            the criteria form containing an example of the wanted entity (with only some fields initialized).
     *            Count, first element to be retrieved and order can also be specified in the form.
     * @return a list of entities matching the given criteria or empty list
     */
    public List<T> findByCriteria(CriteriaForm<T> criteria);

    /**
     * Inserts a new entity (with null id)
     * 
     * @param item
     *            the entity to insert into database
     */
    public void insert(T item);

    /**
     * Deletes the entity with the given id. It has to exist, or the method will fail. The entity must not have any
     * dependencies, that would violate the foreign key constraint.
     * 
     * @param id
     *            the id of the entity to delete
     */
    public void deleteById(Integer id);

    /**
     * Deletes the entity. It has to exist, or the method will fail. The entity must not have any dependencies, that
     * would violate the foreign key constraint.
     * 
     * @param item
     *            the entity to delete
     */
    public void delete(T item);

    /**
     * Updates an existing entity (persists it's current fields)
     * 
     * @param item
     *            the entity to update
     */
    public void update(T item);

    /**
     * Returns the {@link Class} of the entity this DAO is responsible for.
     * 
     * @return the entity {@link Class}
     */
    public Class<T> getEntityClass();
}
