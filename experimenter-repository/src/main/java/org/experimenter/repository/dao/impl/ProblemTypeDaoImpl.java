package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ProblemTypeDao;
import org.experimenter.repository.entity.ProblemType;

public class ProblemTypeDaoImpl extends AbstractBaseDaoImpl<ProblemType> implements ProblemTypeDao {

    @Override
    public Class<ProblemType> getEntityClass() {
        return ProblemType.class;
    }

    @Override
    protected void removeFromAssociations(ProblemType item) {
        // do nothing
    }

}
