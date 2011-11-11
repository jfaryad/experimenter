package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.model.Application;
import org.experimenter.repository.model.Program;
import org.experimenter.repository.util.DaoTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:repositoryContextTest.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ApplicationDaoTest {

    @Autowired
    private ApplicationDao applicationDao;

    @Test
    public void insertApplication() {
        Application application = new Application();
        application.setVersion("1.2");
        application.setExecutable("run.sh");
        Program program = new Program();
        program.setProgramId(1);
        application.setProgram(program);
        applicationDao.insert(application);
        assertNotNull("applicationId is null after insert", application.getApplicationId());
        assertEquals("1.2", application.getVersion());
        assertEquals("run.sh", application.getExecutable());
        assertEquals(1, application.getProgram().getProgramId().intValue());
    }

    @Test
    public void findApplicationById() {
        Integer id = 1;
        Application application = applicationDao.findById(id);
        DaoTestHelper.checkApplication1(application);
    }

    @Test
    public void deleteApplication() {
        Integer id = 2;
        applicationDao.deleteById(id);
        assertNull("application was not deleted", applicationDao.findById(id));
    }

    @Test
    public void updateApplication() {
        Integer id = 3;
        Application application = applicationDao.findById(id);
        assertNotNull("application was not found before update", application);
        assertEquals("1.5", application.getVersion());
        application.setVersion("newVersion");
        applicationDao.update(application);
        application = applicationDao.findById(id);
        assertNotNull("application was not found after update", application);
        assertEquals("newVersion", application.getVersion());
    }

    @Test
    public void findApplicationByCriteria() {
        Application model = new Application();
        model.setVersion("1.3");
        CriteriaForm<Application> criteria = new CriteriaForm<Application>(model);
        List<Application> applications = applicationDao.findByCriteria(criteria);
        assertEquals("wrong number of applications found", 1, applications.size());
        Application application = applications.get(0);
        DaoTestHelper.checkApplication1(application);
    }

}
