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
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findUsersByUserGroupTest() {
        UserGroup userGroup = new UserGroup(1);
        List<User> users = userService.findUsersByUserGroup(userGroup);
        assertEquals("wrong number of users found", 1, users.size());
        User user = users.get(0);
        assertNotNull("user not found", user);
        DaoTestHelper.checkUser1(user);
    }

    @Test
    public void addUserToUserGroupTest() {
        UserGroup userGroup = new UserGroup(2);
        List<User> users = userService.findUsersByUserGroup(userGroup);
        assertEquals("wrong number of users found before test", 0, users.size());
        User user = userService.findById(4);
        userService.addUserToUserGroup(user, userGroup);
        users = userService.findUsersByUserGroup(userGroup);
        assertEquals("wrong number of users found after test", 1, users.size());
    }

    @Test
    public void removeUserFromUserGroupTest() {
        UserGroup userGroup = new UserGroup(5);
        List<User> users = userService.findUsersByUserGroup(userGroup);
        assertEquals("wrong number of users found before test", 1, users.size());
        User user = userService.findById(5);
        userService.removeUserFromUserGroup(user, userGroup);
        users = userService.findUsersByUserGroup(userGroup);
        assertEquals("wrong number of users found after test", 0, users.size());
    }

}
