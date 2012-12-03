package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.testutil.DaoTestHelper;
import org.junit.Test;

public class UserServiceTest extends AbstractServiceTest {

    @Test
    public void findUsersByUserGroupTest() {
        UserGroup userGroup = userGroupService.findById(1);
        List<User> users = userService.findUsersByUserGroup(userGroup);
        assertEquals("wrong number of users found", 1, users.size());
        User user = users.get(0);
        assertNotNull("user not found", user);
        DaoTestHelper.checkUser1(user);
    }

    @Test
    public void addUserToUserGroupTest() {
        UserGroup userGroup = userGroupService.findById(2);
        List<User> users = userService.findUsersByUserGroup(userGroup);
        assertEquals("wrong number of users found before test", 0, users.size());
        User user = userService.findById(4);
        userService.addUserToUserGroup(user, userGroup);
        users = userService.findUsersByUserGroup(userGroup);
        assertEquals("wrong number of users found after test", 1, users.size());
    }

    @Test
    public void removeUserFromUserGroupTest() {
        UserGroup userGroup = userGroupService.findById(5);
        List<User> users = userService.findUsersByUserGroup(userGroup);
        assertEquals("wrong number of users found before test", 1, users.size());
        User user = userService.findById(5);
        userService.removeUserFromUserGroup(user, userGroup);
        users = userService.findUsersByUserGroup(userGroup);
        assertEquals("wrong number of users found after test", 0, users.size());
    }

}
