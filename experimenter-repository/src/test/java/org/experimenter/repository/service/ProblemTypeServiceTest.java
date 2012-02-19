package org.experimenter.repository.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ProblemTypeServiceTest extends AbstractServiceTest {

    @Test
    public void testDeleteDependencies() {
        assertNotNull("project doesn't exist before deleting", projectService.findById(6));
        assertNotNull("program doesn't exist before deleting", programService.findById(4));
        assertNotNull("application doesn't exist before deleting", applicationService.findById(4));
        assertNotNull("experiment doesn't exist before deleting", experimentService.findById(5));
        assertNotNull("input set doesn't exist before deleting", inputSetService.findById(5));
        assertNotNull("input doesn't exist before deleting", inputService.findById(4));

        problemTypeService.delete(problemTypeService.findById(4));

        assertNull("project wasn't deleted", projectService.findById(6));
        assertNull("program wasn't deleted", programService.findById(4));
        assertNull("application wasn't deleted", applicationService.findById(4));
        assertNull("experiment wasn't deleted", experimentService.findById(5));
        assertNull("input set wasn't deleted", inputSetService.findById(5));
        assertNull("input wasn't deleted", inputService.findById(4));
    }

}
