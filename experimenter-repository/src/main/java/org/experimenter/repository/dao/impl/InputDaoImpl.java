package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.InputDao;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.form.SimpleForm;
import org.sqlproc.engine.SqlSession;

public class InputDaoImpl extends AbstractBaseDaoImpl<Input> implements InputDao {

    @Override
    public Class<Input> getEntityClass() {
        return Input.class;
    }

    @Override
    public String getTableName() {
        return Constants.INPUT;
    }

    @Override
    public List<Input> findInputsByInputSet(InputSet inputSet) {
        logger.debug(">> findInputsByInputSet: " + inputSet);
        SqlSession session = getSqlSession();
        String engineName = "FIND_INPUT_BY_INPUT_SET";
        List<Input> inputs = getQueryEngine(engineName).query(session, getEntityClass(),
                new SimpleForm(inputSet.getId()));
        logger.debug("<< findInputsByInputSet: number of inputs found:" + inputs.size());
        return inputs;
    }

}
