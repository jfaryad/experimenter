package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.BaseDao;
import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.form.SimpleForm;
import org.sqlproc.engine.SqlSession;

public abstract class AbstractBaseDaoImpl<T extends Entity> extends AbstractDao implements BaseDao<T> {

    @Override
    public T findById(Integer id) {
        logger.debug(">> findById: " + id);
        SqlSession session = getSqlSession();
        String engineName = "GET_" + getTableName() + "_BY_ID";
        T item = getCrudEngine(engineName).get(session, getEntityClass(), new SimpleForm(id));
        logger.debug("<< findById: " + item);
        return item;
    }

    @Override
    public List<T> findByCriteria(CriteriaForm<T> criteria) {
        logger.debug(">> findByCriteria: " + criteria);
        SqlSession session = getSqlSession();
        String engineName = "GET_" + getTableName() + "_BY_CRITERIA";
        List<T> items = getQueryEngine(engineName).query(session, getEntityClass(), criteria.getModel(), null,
                criteria.getOrder(), 0, criteria.getFirst(), criteria.getCount());
        logger.debug("<< findByCriteria: number of found entries:" + items.size());
        return items;
    }

    @Override
    public void insert(T item) {
        logger.debug(">> insert: " + item);
        SqlSession session = getSqlSession();
        String engineName = "INSERT_" + getTableName();
        getCrudEngine(engineName).insert(session, item);
        logger.debug("<< insert: " + item);
    }

    @Override
    public void deleteById(Integer id) {
        logger.debug(">> deleteById: " + id);
        SqlSession session = getSqlSession();
        String engineName = "DELETE_" + getTableName() + "_BY_ID";
        int deletedRows = getCrudEngine(engineName).delete(session, new SimpleForm(id));
        logger.debug("<< deleteById: number of rows deleted: " + deletedRows);
        return;
    }

    @Override
    public void update(T item) {
        logger.debug("update: " + item);
        SqlSession session = getSqlSession();
        String engineName = "UPDATE_" + getTableName();
        int updatedRows = getCrudEngine(engineName).update(session, item);
        logger.debug("update: number of rows updated: " + updatedRows);
        return;
    }

    @Override
    public abstract Class<T> getEntityClass();

    public abstract String getTableName();
}
