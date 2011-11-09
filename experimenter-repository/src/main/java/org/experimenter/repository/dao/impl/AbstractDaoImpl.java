package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.BaseDao;
import org.experimenter.repository.form.ModelCriteria;
import org.experimenter.repository.model.Model;
import org.experimenter.repository.util.Logging;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public abstract class AbstractDaoImpl<T extends Model> implements BaseDao<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected SessionFactory sessionFactory;
    protected String tableName;

    @SuppressWarnings("unchecked")
    @Override
    public T findById(Integer id) {
        Logging.logTraceDebug(logger, ">> findById: ", id);
        T item = (T) getSession().get(getModelClass(), id);
        Logging.logTraceDebugModel(logger, "<< findById: ", item);
        return item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByCriteria(ModelCriteria<T> criteria) {
        Logging.logTraceDebug(logger, ">> findByCriteria: ", criteria);
        List<T> items = getSession().createCriteria(getModelClass())
                .add(Example.create(criteria.getModel()).enableLike(MatchMode.EXACT)).list();
        Logging.logTraceDebug(logger, "<< findByCriteria: number of found entries:", items.size());
        return items;
    }

    @Override
    public void insert(T item) {
        Logging.logTraceDebugModel(logger, ">> insert: ", item);
        getSession().save(item);
        Logging.logTraceDebugModel(logger, "<< insert: ", item);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteById(Integer id) {
        Logging.logTraceDebug(logger, ">> deleteById: ", id);
        T item = (T) getSession().load(getModelClass(), id);
        delete(item);
        Logging.logTraceDebug(logger, "<< deleteById");
        return;
    }

    @Override
    public void delete(T item) {
        Logging.logTraceDebugModel(logger, ">> delete: ", item);
        getSession().delete(item);
        Logging.logTraceDebug(logger, "<< delete");
        return;
    }

    @Override
    public void update(T item) {
        Logging.logTraceDebugModel(logger, "update: ", item);
        getSession().update(item);
        Logging.logTraceDebugModel(logger, "update: ", item);
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
