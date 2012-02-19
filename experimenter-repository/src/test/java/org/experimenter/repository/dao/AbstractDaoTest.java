package org.experimenter.repository.dao;

import org.hibernate.SessionFactory;
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
public abstract class AbstractDaoTest {

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected ApplicationDao applicationDao;

    @Autowired
    protected ComputerDao computerDao;

    @Autowired
    protected ConnectionDao connectionDao;

    @Autowired
    protected ConnectionFarmDao connectionFarmDao;

    @Autowired
    protected ExperimentDao experimentDao;

    @Autowired
    protected InputDao inputDao;

    @Autowired
    protected InputSetDao inputSetDao;

    @Autowired
    protected ProblemTypeDao problemTypeDao;

    @Autowired
    protected ProgramDao programDao;

    @Autowired
    protected ProjectDao projectDao;

    @Autowired
    protected UserGroupDao userGroupDao;

    @Autowired
    protected UserDao userDao;

    protected void flush() {
        sessionFactory.getCurrentSession().flush();
    }

}
