package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.testutil.DaoTestHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserGroupDaoTest extends AbstractDaoTest {

    @Autowired
    private UserGroupDao userGroupDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void insertUserGroup() {
        UserGroup userGroup = new UserGroup();
        userGroup.setName("geeks");
        userGroupDao.insert(userGroup);
        assertNotNull("userGroupId is null after insert", userGroup.getId());
        userGroup = userGroupDao.findById(userGroup.getId());
        assertEquals("geeks", userGroup.getName());
    }

    @Test
    public void findUserGroupById() {
        Integer id = 1;
        UserGroup userGroup = userGroupDao.findById(id);
        DaoTestHelper.checkUserGroup1(userGroup);
        DaoTestHelper.checkUser1(userGroup.getUsers().get(0));
        DaoTestHelper.checkConnectionFarm1(userGroup.getConnectionFarms().get(0));
    }

    @Test
    public void deleteUserGroup() {
        Integer id = 4;
        assertEquals(1, userDao.findById(2).getUserGroups().size());
        userGroupDao.deleteById(id);
        flush();
        assertNull("userGroup was not deleted", userGroupDao.findById(id));
        assertEquals(0, userDao.findById(2).getUserGroups().size());

    }

    @Test
    public void updateUserGroup() {
        Integer id = 3;
        UserGroup userGroup = userGroupDao.findById(id);
        assertNotNull("userGroup was not found before update", userGroup);
        assertEquals("public", userGroup.getName());
        userGroup.setName("private");
        userGroupDao.update(userGroup);
        userGroup = userGroupDao.findById(id);
        assertNotNull("userGroup was not found after update", userGroup);
        assertEquals("private", userGroup.getName());
    }

    @Test
    public void findUserGroupByCriteria() {
        UserGroup model = new UserGroup();
        model.setName("students");
        CriteriaForm<UserGroup> criteria = new CriteriaForm<UserGroup>(model);
        List<UserGroup> userGroups = userGroupDao.findByCriteria(criteria);
        assertEquals("wrong number of userGroups found", 1, userGroups.size());
        UserGroup userGroup = userGroups.get(0);
        DaoTestHelper.checkUserGroup1(userGroup);
    }

}
