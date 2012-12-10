package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.InputDao;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;

/**
 * Default implementation of InputDao
 * 
 * @author jfaryad
 * 
 */

public class InputDaoImpl extends AbstractBaseDaoImpl<Input> implements InputDao {

    @Override
    public Class<Input> getEntityClass() {
        return Input.class;
    }

    @Override
    public void removeFromAssociations(Input input) {
        input.getProblem().getInputs().remove(input);
        for (InputSet inputSet : input.getInputSets())
            inputSet.getInputs().remove(input);
    }

    @Override
    public Input findInputByChecksum(String checksum) {
        logger.debug(">> findInputByChecksum: " + checksum);
        Input input = (Input) getSession().getNamedQuery(Input.Q_GET_BY_CHECKSUM)
                .setString("checksum", checksum)
                .uniqueResult();
        logger.debug("<< findInputByChecksum: found:" + input);
        return input;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Input> findInputsByExperiment(Experiment experiment) {
        logger.debug(">> findInputsByExperiment: " + experiment);
        List<Input> inputs = getSession().getNamedQuery(Input.Q_GET_BY_EXPERIMENT)
                .setEntity("checksum", experiment)
                .list();
        logger.debug("<< findInputsByExperiment: number found:" + inputs.size());
        return inputs;
    }

}
