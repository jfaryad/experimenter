package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ConnectionDao;
import org.experimenter.repository.model.Connection;

public class ConnectionDaoImpl extends AbstractBaseDaoImpl<Connection> implements ConnectionDao {

	@Override
	public Class<Connection> getModelClass() {
		return Connection.class;
	}

}
