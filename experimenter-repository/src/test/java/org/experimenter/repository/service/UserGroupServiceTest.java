package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.testutil.DaoTestHelper;
import org.junit.Test;

public class UserGroupServiceTest extends AbstractServiceTest {

    @Test
    public void findUserGroupsByUserTest() {
        User user = userService.findById(1);
        List<UserGroup> userGroups = userGroupService.findUserGroupsByUser(user);
        assertEquals("wrong number of userGroups found", 1, userGroups.size());
        UserGroup userGroup = userGroups.get(0);
        assertNotNull("userGroup not found", userGroup);
        DaoTestHelper.checkUserGroup1(userGroup);
    }

    @Test
    public void testDeleteDependencies() {
        assertNotNull("project doesn't exist before deleting", projectService.findById(6));
        assertNotNull("program doesn't exist before deleting", programService.findById(4));
        assertNotNull("application doesn't exist before deleting", applicationService.findById(4));
        assertNotNull("experiment doesn't exist before deleting", experimentService.findById(5));
        assertNotNull("connection farm doesn't exist before deleting", connectionFarmService.findById(4));
        assertNotNull("connection doesn't exist before deleting", connectionService.findById(4));

        userGroupService.delete(userGroupService.findById(5));

        assertNull("project wasn't deleted", projectService.findById(6));
        assertNull("program wasn't deleted", programService.findById(4));
        assertNull("application wasn't deleted", applicationService.findById(4));
        assertNull("experiment wasn't deleted", experimentService.findById(5));
        assertNull("connection wasn't deleted", connectionFarmService.findById(4));
        assertNull("connection wasn't deleted", connectionService.findById(4));
    }

}