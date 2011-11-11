package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ProblemTypeDao;
import org.experimenter.repository.model.ProblemType;

public class ProblemTypeDaoImpl extends AbstractBaseDaoImpl<ProblemType> implements ProblemTypeDao {

	@Override
	public Class<ProblemType> getModelClass() {
		return ProblemType.class;
	}

}
