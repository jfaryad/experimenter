package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
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
public class UserGroupServiceTest {

    @Autowired
    private UserGroupService userGroupService;

    @Test
    public void findUserGroupsByUserTest() {
        User user = new User(1);
        List<UserGroup> userGroups = userGroupService.findUserGroupsByUser(user);
        assertEquals("wrong number of userGroups found", 1, userGroups.size());
        UserGroup userGroup = userGroups.get(0);
        assertNotNull("userGroup not found", userGroup);
        DaoTestHelper.checkUserGroup1(userGroup);
    }

}