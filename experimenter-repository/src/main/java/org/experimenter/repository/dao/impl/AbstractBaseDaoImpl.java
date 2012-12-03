package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.BaseDao;
import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.form.CriteriaForm;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * The base to all implementations of entity DAOs. It implements the methods from BaseDao and provides additional common
 * useful methods.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public abstract class AbstractBaseDaoImpl<T extends Entity> implements BaseDao<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public T findById(Integer id) {
        if (logger.isDebugEnabled())
            logger.debug(">> findById: " + id);

        T item = (T) getSession().get(getEntityClass(), id);

        if (logger.isDebugEnabled())
            logger.debug("<< findById: " + item);
        return item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByCriteria(CriteriaForm<T> criteria) {
        if (logger.isDebugEnabled())
            logger.debug(">> findByCriteria: " + criteria);

        Criteria crit = getSession().createCriteria(getEntityClass()).add(
                Example.create(criteria.getEntity()).enableLike(MatchMode.EXACT));
        for (Order order : criteria.getOrder())
            crit.addOrder(order);
        if (criteria.getFirst() != null)
            crit.setFirstResult(criteria.getFirst());
        if (criteria.getCount() != null)
            crit.setMaxResults(criteria.getCount());
        List<T> items = crit.list();

        if (logger.isDebugEnabled())
            logger.debug("<< findByCriteria: number of found entries:" + items.size());
        return items;
    }

    @Override
    public void insert(T item) {
        if (logger.isDebugEnabled())
            logger.debug(">> insert: " + item);

        getSession().save(item);

        if (logger.isDebugEnabled())
            logger.debug("<< insert: " + item);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteById(Integer id) {
        if (logger.isDebugEnabled())
            logger.debug(">> deleteById: " + id);

        T item = (T) getSession().load(getEntityClass(), id);
        delete(item);

        if (logger.isDebugEnabled())
            logger.debug("<< deleteById");
        return;
    }

    @Override
    public void delete(T item) {
        if (logger.isDebugEnabled())
            logger.debug(">> delete: " + item);

        removeFromAssociations(item);
        getSession().delete(item);

        if (logger.isDebugEnabled())
            logger.debug("<< delete");
        return;
    }

    @Override
    public void update(T item) {
        if (logger.isDebugEnabled())
            logger.debug(">> update: " + item);

        getSession().update(item);

        if (logger.isDebugEnabled())
            logger.debug("<< update");
        return;
    }

    /**
     * Removes the entity from all associations in other entities to keep them up to date, when the item is deleted.
     * 
     * @param item
     *            the entity, that is going to be deleted
     */
    protected abstract void removeFromAssociations(T item);

    @Override
    public abstract Class<T> getEntityClass();

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
