package org.experimenter.repository.dao.impl;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.InputDao;
import org.experimenter.repository.entity.Input;

public class InputDaoImpl extends AbstractBaseDaoImpl<Input> implements InputDao {

    @Override
    public Class<Input> getEntityClass() {
        return Input.class;
    }

    @Override
    public String getTableName() {
        return Constants.INPUT;
    }

}
