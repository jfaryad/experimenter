package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.BaseDao;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.form.SimpleForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlproc.engine.SqlCrudEngine;
import org.sqlproc.engine.SqlEngineFactory;
import org.sqlproc.engine.SqlOrder;
import org.sqlproc.engine.SqlQueryEngine;
import org.sqlproc.engine.SqlSession;
import org.sqlproc.engine.spring.SpringSimpleSession;

public abstract class AbstractDaoImpl<T> implements BaseDao<T> {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected JdbcTemplate jdbcTemplate;
	protected SqlEngineFactory sqlFactory;
	protected String tableName;

	@Override
	public T findById(Integer id) {
		logger.debug(">> findById: " + id);
		SqlSession session = getSqlSession();
		String engineName = "GET_" + getTableName() + "_BY_ID";
		T item = getCrudEngine(engineName).get(session, getModelClass(),
				new SimpleForm(id));
		logger.debug("<< findById: " + item);
		return item;
	}

	@Override
	public List<T> findByCriteria(CriteriaForm<T> criteria) {
		logger.debug(">> findByCriteria: " + criteria);
		SqlSession session = getSqlSession();
		List<T> items = getQueryEngine("ITEMS").query(session, getModelClass(),
				criteria.getModel(), null,
				SqlOrder.getOrder(criteria.getOrder()), 0, criteria.getCount(),
				criteria.getFirst());
		logger.debug(">> findByCriteria: number of found entries:"
				+ items.size());
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
		logger.debug(">> delete: " + id);
		SqlSession session = getSqlSession();
		String engineName = "DELETE_" + getTableName() + "_BY_ID";
		int deletedRows = getCrudEngine(engineName).delete(session,
				new SimpleForm(id));
		logger.debug("<< delete: number of rows deleted" + deletedRows);
		return;
	}

	@Override
	public void update(T item) {
		SqlSession session = getSqlSession();
		logger.debug("update: " + item);
		int updatedRows = getCrudEngine("UPDATE_ITEM").update(session, item);
		logger.debug("update: number of rows updated" + updatedRows);
		return;
	}

	@Override
	public abstract Class<T> getModelClass();

	protected SqlSession getSqlSession() {
		SqlSession session = new SpringSimpleSession(jdbcTemplate);
		return session;
	}

	public SqlCrudEngine getCrudEngine(String name) {
		SqlCrudEngine queryEngine = sqlFactory.getCrudEngine(name);
		if (queryEngine == null)
			throw new RuntimeException("Missing SqlQueryEngine " + name);
		return queryEngine;
	}

	public SqlQueryEngine getQueryEngine(String name) {
		SqlQueryEngine queryEngine = sqlFactory.getQueryEngine(name);
		if (queryEngine == null)
			throw new RuntimeException("Missing SqlQueryEngine " + name);
		return queryEngine;
	}

	@Required
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	@Required
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Required
	public void setSqlFactory(SqlEngineFactory sqlFactory) {
		this.sqlFactory = sqlFactory;
	}
}
