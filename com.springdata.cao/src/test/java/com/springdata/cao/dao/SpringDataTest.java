package com.springdata.cao.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringDataTest {
	private ApplicationContext ctx = null;
	
	@Before
	public void setUp() {
		ctx = new ClassPathXmlApplicationContext("beans-new.xml");
		System.out.println("Set Up");
	}
	
	@After
	public void clear() {
		ctx = null;
		System.out.println("Clear");
	}
	
	@Test
	public void testEntityManagerFactory() {
		System.out.println("generate table auto");
	}
    
    
}
