package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.InputDao;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;

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

    // @Override
    // public List<Input> findInputsByInputSet(InputSet inputSet) {
    // logger.debug(">> findInputsByInputSet: " + inputSet);
    // SqlSession session = getSqlSession();
    // String engineName = "FIND_INPUT_BY_INPUT_SET";
    // List<Input> inputs = getQueryEngine(engineName).query(session, getEntityClass(),
    // new SimpleForm(inputSet.getId()));
    // logger.debug("<< findInputsByInputSet: number of inputs found:" + inputs.size());
    // return inputs;
    // }

}
