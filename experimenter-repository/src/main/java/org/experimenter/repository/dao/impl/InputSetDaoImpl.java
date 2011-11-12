package org.experimenter.repository.dao.impl;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.InputSetDao;
import org.experimenter.repository.entity.InputSet;

public class InputSetDaoImpl extends AbstractBaseDaoImpl<InputSet> implements InputSetDao {

    @Override
    public Class<InputSet> getEntityClass() {
        return InputSet.class;
    }

    @Override
    public String getTableName() {
        return Constants.INPUT_SET;
    }

}
