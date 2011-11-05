package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.experimenter.repository.model.User;
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
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Test
	public void insertUser() {
		User user = new User();
		user.setName("Josef");
		user.setSurname("Novak");
		user.setLogin("pepa");
		user.setPassword("pepa123");
		user.setEmail("pepa@novak.cz");
		userDao.insert(user);
		assertNotNull("userId is null after insert", user.getUserId());
	}

	@Test
	public void findUserById() {
		Integer id = 1;
		assertNotNull("user not found", userDao.findById(id));
	}

	@Test
	public void deleteUser() {
		Integer id = 2;
		userDao.deleteById(id);
		assertNull("user was not deleted", userDao.findById(id));
	}

}
