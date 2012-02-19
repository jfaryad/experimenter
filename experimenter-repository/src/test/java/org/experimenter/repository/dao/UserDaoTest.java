package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.util.DaoTestHelper;
import org.junit.Test;

public class UserDaoTest extends AbstractDaoTest {

    @Test
    public void insertUser() {
        User user = new User();
        user.setName("Josef");
        user.setSurname("Novak");
        user.setLogin("pepa");
        user.setPassword("pepa123");
        user.setEmail("pepa@novak.cz");
        userDao.insert(user);
        assertNotNull("userId is null after insert", user.getId());
        user = userDao.findById(user.getId());
        assertEquals("Josef", user.getName());
        assertEquals("Novak", user.getSurname());
        assertEquals("pepa", user.getLogin());
        assertEquals("pepa123", user.getPassword());
        assertEquals("pepa@novak.cz", user.getEmail());
    }

    @Test
    public void findUserById() {
        Integer id = 1;
        User user = userDao.findById(id);
        assertNotNull("user not found", user);
        DaoTestHelper.checkUser1(user);
        DaoTestHelper.checkUserGroup1(user.getUserGroups().get(0));
    }

    @Test
    public void deleteUser() {
        Integer id = 2;
        User user = userDao.findById(id);
        assertNotNull("User to delete is null", user);
        assertEquals(1, userDao.findUsersByUserGroup(userGroupDao.findById(4)).size());
        userDao.deleteById(id);
        flush();
        assertNull("user was not deleted", userDao.findById(id));
        assertEquals(0, userDao.findUsersByUserGroup(userGroupDao.findById(4)).size());
    }

    @Test
    public void updateUser() {
        Integer id = 3;
        User user = userDao.findById(id);
        assertNotNull("user was not found before update", user);
        assertEquals("Tester3", user.getName());
        user.setName("newName");
        userDao.update(user);
        user = userDao.findById(id);
        assertNotNull("user was not found after update", user);
        assertEquals("newName", user.getName());
    }

    @Test
    public void findUserByCriteria() {
        User model = new User();
        model.setEmail("tester1@experimenter.org");
        CriteriaForm<User> criteria = new CriteriaForm<User>(model);
        List<User> users = userDao.findByCriteria(criteria);
        assertEquals("wrong number of users found", 1, users.size());
        User user = users.get(0);
        assertNotNull("user not found", user);
        DaoTestHelper.checkUser1(user);
    }

}
