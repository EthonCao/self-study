package com.springdata.cao.dao;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

public class DataSourceTest {

	private ApplicationContext ctx = null;
	
	@Before
	public void setUp() {
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("Set Up");
	}
	
	@After
	public void clear() {
		ctx = null;
		System.out.println("CLear");
	}
	@Test
	public void testJDBCTemplate() {
		System.out.println("Test JDBC Template");
		JdbcTemplate template = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		Assert.notNull(template);
	}
}
