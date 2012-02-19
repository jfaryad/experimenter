package org.experimenter.repository.service;

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
public abstract class AbstractServiceTest {

    @Autowired
    protected ApplicationService applicationService;

    @Autowired
    protected ComputerService computerService;

    @Autowired
    protected ConnectionService connectionService;

    @Autowired
    protected ConnectionFarmService connectionFarmService;

    @Autowired
    protected ExperimentService experimentService;

    @Autowired
    protected InputService inputService;

    @Autowired
    protected InputSetService inputSetService;

    @Autowired
    protected ProblemTypeService problemTypeService;

    @Autowired
    protected ProgramService programService;

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected UserGroupService userGroupService;

    @Autowired
    protected UserService userService;

}
