package org.experimenter.repository.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BasicTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        int a = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM USER");
        assertEquals(1, a);
    }
}
