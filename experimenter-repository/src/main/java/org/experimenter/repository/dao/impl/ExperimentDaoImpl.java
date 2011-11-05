package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ExperimentDao;
import org.experimenter.repository.model.Experiment;

public class ExperimentDaoImpl extends AbstractDaoImpl<Experiment> implements ExperimentDao {

	@Override
	public Class<Experiment> getModelClass() {
		return Experiment.class;
	}

}
