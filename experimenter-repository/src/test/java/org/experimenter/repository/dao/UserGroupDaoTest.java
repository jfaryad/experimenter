package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.model.UserGroup;
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
public class UserGroupDaoTest {

    @Autowired
    private UserGroupDao userGroupDao;

    @Test
    public void insertUserGroup() {
        UserGroup userGroup = new UserGroup();
        userGroup.setName("geeks");
        userGroupDao.insert(userGroup);
        assertNotNull("userGroupId is null after insert", userGroup.getUserGroupId());
        userGroup = userGroupDao.findById(userGroup.getUserGroupId());
        assertEquals("geeks", userGroup.getName());
    }

    @Test
    public void findUserGroupById() {
        Integer id = 1;
        UserGroup userGroup = userGroupDao.findById(id);
        DaoTestHelper.checkUserGroup1(userGroup);
    }

    @Test
    public void deleteUserGroup() {
        Integer id = 4;
        userGroupDao.deleteById(id);
        assertNull("userGroup was not deleted", userGroupDao.findById(id));
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
