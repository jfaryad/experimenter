package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.InputSetDao;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;

/**
 * Default implementation of {@link InputSetDao}
 * 
 * @author jfaryad
 * 
 */
public class InputSetDaoImpl extends AbstractBaseDaoImpl<InputSet> implements InputSetDao {

    @Override
    public Class<InputSet> getEntityClass() {
        return InputSet.class;
    }

    @Override
    public void removeFromAssociations(InputSet inputSet) {
        inputSet.getProblem().getInputSets().remove(inputSet);
        for (Input input : inputSet.getInputs())
            input.getInputSets().remove(inputSet);
        for (Experiment experiment : inputSet.getExperiments())
            experiment.getInputSets().remove(inputSet);
        for (Project project : inputSet.getProjects())
            project.getInputSets().remove(inputSet);
    }

}
