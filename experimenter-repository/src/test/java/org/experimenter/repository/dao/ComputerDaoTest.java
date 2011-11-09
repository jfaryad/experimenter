package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.form.ModelCriteria;
import org.experimenter.repository.model.Computer;
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
public class ComputerDaoTest extends AbstractTest {

    @Autowired
    private ComputerDao computerDao;

    @Test
    public void insertComputer() {
        Computer computer = new Computer();
        computer.setAddress("localhost");
        computer.setDescription("localhost");
        computerDao.insert(computer);
        assertNotNull("computerId is null after insert", computer.getComputerId());
        assertNotNull("setId is null after insert", computer.getComputerId());
        assertEquals("localhost", computer.getAddress());
        assertEquals("localhost", computer.getDescription());
    }

    @Test
    public void findComputerById() {
        Integer id = 1;
        Computer computer = computerDao.findById(id);
        assertNotNull("computer not found", computer);
        assertEquals("u-pl20", computer.getAddress());
        assertEquals("computer u-pl20, test exists", computer.getDescription());
    }

    @Test
    public void deleteComputer() {
        Integer id = 2;
        computerDao.deleteById(id);
        assertNull("computer was not deleted", computerDao.findById(id));
    }

    @Test
    public void updateComputer() {
        Integer id = 3;
        Computer computer = computerDao.findById(id);
        assertNotNull("computer was not found before update", computer);
        assertEquals("u-pl22", computer.getAddress());
        computer.setAddress("newAddress");
        computerDao.update(computer);
        computer = computerDao.findById(id);
        assertNotNull("computer was not found after update", computer);
        assertEquals("newAddress", computer.getAddress());
    }

    @Test
    public void findComputerByCriteria() {
        Computer model = new Computer();
        model.setAddress("u-pl20");
        ModelCriteria<Computer> criteria = new ModelCriteria<Computer>(model);
        List<Computer> computers = computerDao.findByCriteria(criteria);
        assertEquals("wrong number of computers found", 1, computers.size());
        Computer computer = computers.get(0);
        assertNotNull("computer not found", computer);
        assertEquals("u-pl20", computer.getAddress());
        assertEquals("computer u-pl20, test exists", computer.getDescription());
    }

}
