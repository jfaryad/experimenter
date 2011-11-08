package org.experimenter.repository.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.experimenter.repository.dao.BaseDao;
import org.experimenter.repository.form.CriteriaForm;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public abstract class AbstractDaoImpl<T extends Serializable> implements BaseDao<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected SessionFactory sessionFactory;
    protected String tableName;

    @SuppressWarnings("unchecked")
    @Override
    public T findById(Integer id) {
        logger.debug(">> findById: " + id);
        try {
            T item = (T) getSession().load(getModelClass(), id);
            logger.debug("<< findById: " + item);
            return item;
        } catch (ObjectNotFoundException ex) {
            logger.debug("!! findById: entry not found");
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByCriteria(CriteriaForm<T> criteria) {
        logger.debug(">> findByCriteria: " + criteria);
        List<T> items = getSession().createCriteria(getModelClass())
                .add(Example.create(criteria.getModel()).enableLike(MatchMode.EXACT)).list();
        logger.debug("<< findByCriteria: number of found entries:" + items.size());
        return items;
    }

    @Override
    public void insert(T item) {
        logger.debug(">> insert: " + item);
        getSession().save(item);
        logger.debug("<< insert: " + item);
    }

    @Override
    public void deleteById(Integer id) {
        logger.debug(">> deleteById: " + id);
        T item = findById(id);
        getSession().delete(item);
        logger.debug("<< deleteById");
        return;
    }

    @Override
    public void update(T item) {
        logger.debug("update: " + item);
        getSession().update(item);
        logger.debug("update: " + item);
        return;
    }

    @Override
    public abstract Class<T> getModelClass();

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Required
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
