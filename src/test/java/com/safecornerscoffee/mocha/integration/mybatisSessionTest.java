package com.safecornerscoffee.mocha.integration;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class mybatisSessionTest {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void sessionTest() throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            assertThat(session).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}