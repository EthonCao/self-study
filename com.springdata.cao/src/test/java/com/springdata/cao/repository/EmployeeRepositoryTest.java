package com.springdata.cao.repository;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springdata.cao.domain.Employee;

import junit.framework.Assert;

public class EmployeeRepositoryTest {

	private ApplicationContext ctx = null;
	private EmployeeRepository employeeRepository = null;
	
	
	@Before
	public void setUp() {
		ctx = new ClassPathXmlApplicationContext("beans-new.xml");
		employeeRepository = ctx.getBean(EmployeeRepository.class);
		System.out.println("Set Up");
	}
	
	@After
	public void clear() {
		ctx = null;
		System.out.println("CLear");
	}
	
	@Test
	public void testFindByName() {
		Employee employee = employeeRepository.findByName("caocao");
		System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge());
	}
	
	@Test
	public void testFindByNameStartingWithAndAgeLessThan() {
		List<Employee> employees = employeeRepository.findByNameStartingWithAndAgeLessThan("test", 22);
		System.out.println("Print Query Result:");
		for (Employee employee : employees) {
			System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge());
		}
	}
	
	@Test
	public void testFindByNameEndingWithAndAgeLessThan() {
		List<Employee> employees = employeeRepository.findByNameEndingWithAndAgeLessThan("6", 22);
		System.out.println("Print Query Result:");
		for (Employee employee : employees) {
			System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge());
		}
	}
	
	@Test
	public void testFindByNameInOrAgeLessThan() {
		List<String> nameList = new ArrayList<String>();
		nameList.add("test1");
		nameList.add("test2");
		nameList.add("test3");
		List<Employee> employees = employeeRepository.findByNameInOrAgeLessThan(nameList, 22);
		System.out.println("Print Query Result:");
		for (Employee employee : employees) {
			System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge());
		}
	}
	
	@Test
	public void testFindByNameInAndAgeLessThan() {
		List<String> nameList = new ArrayList<String>();
		nameList.add("test1");
		nameList.add("test2");
		nameList.add("test3");
		List<Employee> employees = employeeRepository.findByNameInAndAgeLessThan(nameList, 22);
		System.out.println("Print Query Result:");
		for (Employee employee : employees) {
			System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge());
		}
	}
	
	@Test
	public void TestGetEmployeeByMaxId() {
		Employee employee = employeeRepository.getEmployeeByMaxId();
		System.out.println(employee);
		Assert.assertNotNull(employee);
	}
	
	@Test
	public void TestFindEmployeeByParms1() {
		List<Employee> employees = employeeRepository.getEmployeeByParms1("caocao", 20);
		System.out.println(employees);
		Assert.assertNotNull(employees);
	}
	
	@Test
	public void testFindEmployeeByParams2() {
		List<Employee> employees = employeeRepository.findEmployeeByParams2("caocao", 20);
		System.out.println(employees);
		Assert.assertNotNull(employees);

	}
	
	@Test
	public void testFindEmployeeByNameLike1() {
		List<Employee> employees = employeeRepository.findEmployeeByNameLike1("test2");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		for (Employee employee : employees) {
			System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge());
		}
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		Assert.assertNotNull(employees);
	}
	
	@Test
	public void testFindEmployeeByNameLike2() {
		List<Employee> employees = employeeRepository.findEmployeeByNameLike2("test");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		for (Employee employee : employees) {
			System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge());
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Assert.assertNotNull(employees);
	}
}
