package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ConnectionFarmDao;
import org.experimenter.repository.model.ConnectionFarm;

public class ConnectionFarmDaoImpl extends AbstractBaseDaoImpl<ConnectionFarm> implements ConnectionFarmDao {

	@Override
	public Class<ConnectionFarm> getModelClass() {
		return ConnectionFarm.class;
	}

}
