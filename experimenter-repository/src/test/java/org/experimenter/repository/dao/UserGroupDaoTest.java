package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;

import org.experimenter.repository.model.UserGroup;
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
		userGroup.setName("nerds");
		userGroupDao.insert(userGroup);
		assertNotNull("userGroupId is null after insert",
				userGroup.getUserGroupId());
	}

}
