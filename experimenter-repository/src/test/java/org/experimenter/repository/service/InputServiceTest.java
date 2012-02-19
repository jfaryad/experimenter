package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.util.DaoTestHelper;
import org.junit.Test;

public class InputServiceTest extends AbstractServiceTest {

    @Test
    public void findInputsByProblemTypeTest() {
        ProblemType problem = problemTypeService.findById(1);
        List<Input> inputs = inputService.findInputsByProblemType(problem);
        assertEquals("wrong number of inputs found", 2, inputs.size());
        Input input = inputs.get(0);
        assertNotNull("input not found", input);
        DaoTestHelper.checkInput1(input);
    }

    @Test
    public void findInputsByInputSetTest() {
        InputSet inputSet = inputSetService.findById(1);
        List<Input> inputs = inputService.findInputsByInputSet(inputSet);
        assertEquals("wrong number of inputs found", 1, inputs.size());
        Input input = inputs.get(0);
        assertNotNull("input not found", input);
        DaoTestHelper.checkInput1(input);
    }

    @Test
    public void addInputToInputSetTest() {
        InputSet inputSet = inputSetService.findById(4);
        assertEquals("inputSet is not supposed to have associated inputs", 0,
                inputService.findInputsByInputSet(inputSet).size());
        Input input = inputService.findById(1);
        inputService.addInputToInputSet(input, inputSet);
        assertEquals("input hasn't been added to inputSet", 1, inputService.findInputsByInputSet(inputSet).size());
    }

    @Test
    public void removeInputFromInputSetTest() {
        InputSet inputSet = inputSetService.findById(3);
        assertEquals("inputSet is supposed to have an associated input", 1, inputService.findInputsByInputSet(inputSet)
                .size());
        Input input = inputService.findById(2);
        inputService.removeInputFromInputSet(input, inputSet);
        assertEquals("input hasn't been removed from inputSet", 0, inputService.findInputsByInputSet(inputSet).size());
    }
}
