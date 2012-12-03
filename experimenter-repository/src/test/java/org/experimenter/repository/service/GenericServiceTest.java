package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.testutil.DaoTestHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InputService inputService;

    @Autowired
    private ExperimentService experimentService;

    @Test
    public void saveTest() {
        User user = new User();
        user.setName("Josef");
        user.setSurname("Novak");
        user.setLogin("pepa");
        user.setPassword("pepa123");
        user.setEmail("pepa@novak.cz");
        user.setIsAdmin(Boolean.FALSE);
        userService.saveUpdate(user);
        assertNotNull("userId is null after insert", user.getId());
        user = userService.findById(user.getId());
        assertEquals("Josef", user.getName());
        assertEquals("Novak", user.getSurname());
        assertEquals("pepa", user.getLogin());
        assertEquals("pepa123", user.getPassword());
        assertEquals(Boolean.FALSE, user.getIsAdmin());
        assertEquals("pepa@novak.cz", user.getEmail());
    }

    @Test
    public void updateTest() {
        Integer id = 3;
        Project project = projectService.findById(id);
        assertNotNull("project was not found before update", project);
        assertEquals("testProject3", project.getName());
        project.setName("newName");
        projectService.saveUpdate(project);
        project = projectService.findById(id);
        assertNotNull("project was not found after update", project);
        assertEquals("newName", project.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveUpdateNullTest() {
        userService.saveUpdate(null);
    }

    @Test
    public void findByCriteriaTest() {
        Input model = new Input();
        model.setName("testInput1");
        CriteriaForm<Input> criteria = new CriteriaForm<Input>(model);
        List<Input> inputs = inputService.findByCriteria(criteria);
        assertEquals("wrong number of inputs found", 1, inputs.size());
        Input input = inputs.get(0);
        DaoTestHelper.checkInput1(input);
    }

    @Test
    public void findByExampleTest() {
        Experiment model = new Experiment();
        model.setName("experiment1");
        List<Experiment> experiments = experimentService.findByExample(model);
        assertEquals("wrong number of experiments found", 1, experiments.size());
        Experiment experiment = experiments.get(0);
        DaoTestHelper.checkExperiment1(experiment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByExampleNullTest() {
        inputService.findByExample(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByCriteriaNullTest() {
        userService.findByCriteria(new CriteriaForm<User>());
    }

}
