package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.testutil.DaoTestHelper;
import org.junit.Test;

public class ComputerDaoTest extends AbstractDaoTest {

    @Test
    public void insertComputer() {
        Computer computer = new Computer();
        computer.setAddress("localhost");
        computer.setDescription("localhost");
        computer.setNumberOfRunningJobs(0);
        computerDao.insert(computer);
        assertNotNull("computerId is null after insert", computer.getId());
        assertNotNull("setId is null after insert", computer.getId());
        assertEquals("localhost", computer.getAddress());
        assertEquals("localhost", computer.getDescription());
    }

    @Test
    public void findComputerById() {
        Integer id = 1;
        Computer computer = computerDao.findById(id);
        DaoTestHelper.checkComputer1(computer);
        DaoTestHelper.checkConnection1(computer.getConnections().get(0));
    }

    @Test
    public void deleteComputer() {
        Integer id = 2;
        computerDao.deleteById(id);
        flush();
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
        CriteriaForm<Computer> criteria = new CriteriaForm<Computer>(model);
        List<Computer> computers = computerDao.findByCriteria(criteria);
        assertEquals("wrong number of computers found", 1, computers.size());
        Computer computer = computers.get(0);
        DaoTestHelper.checkComputer1(computer);
    }

}
