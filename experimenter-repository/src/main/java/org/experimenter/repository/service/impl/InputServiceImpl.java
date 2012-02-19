package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.InputDao;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.InputService;

public class InputServiceImpl extends AbstractService<Input, InputDao> implements InputService {

    @Override
    protected void deleteDependencies(Input input) {
        // nothing to do
    }

    @Override
    public void addInputToInputSet(Input input, InputSet inputSet) {
        input.getInputSets().add(inputSet);
        inputSet.getInputs().add(input);

    }

    @Override
    public void removeInputFromInputSet(Input input, InputSet inputSet) {
        input.getInputSets().remove(inputSet);
        inputSet.getInputs().remove(input);
    }

    @Override
    public List<Input> findInputsByProblemType(ProblemType problemType) {
        checkIdNotNull(problemType);
        return problemType.getInputs();
    }

    @Override
    public List<Input> findInputsByInputSet(InputSet inputSet) {
        checkIdNotNull(inputSet);
        return inputSet.getInputs();
    }

}
