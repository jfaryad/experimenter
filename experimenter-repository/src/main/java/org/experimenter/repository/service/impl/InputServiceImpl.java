package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.InputDao;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.service.InputService;

public class InputServiceImpl extends AbstractService<Input, InputDao> implements InputService {

    @Override
    protected void deleteDependencies(Input input) {
        junctionDao.removeInputFromInputSet(input, null);
    }

    @Override
    public void addInputToInputSet(Input input, InputSet inputSet) {
        junctionDao.addInputToInputSet(input, inputSet);

    }

    @Override
    public void removeInputFromInputSet(Input input, InputSet inputSet) {
        junctionDao.removeInputFromInputSet(input, inputSet);

    }

    @Override
    public List<Input> findInputsByProblemType(ProblemType problemType) {
        checkIdNotNull(problemType);
        Input input = new Input();
        input.setProblem(problemType);
        CriteriaForm<Input> criteria = new CriteriaForm<Input>(input);
        return baseDao.findByCriteria(criteria);
    }

    @Override
    public List<Input> findInputsByInputSet(InputSet inputSet) {
        checkIdNotNull(inputSet);
        return baseDao.findInputsByInputSet(inputSet);
    }

}
