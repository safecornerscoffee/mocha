package com.safecornerscoffee.mocha.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class jdbcConnectionTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void connectionTest() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
