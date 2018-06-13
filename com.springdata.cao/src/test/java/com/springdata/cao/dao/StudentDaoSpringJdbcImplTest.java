package com.springdata.cao.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springdata.cao.domain.Student;

public class StudentDaoSpringJdbcImplTest {
	
	private ApplicationContext ctx = null;
	private StudentDao studentDao = null;
	
	@Before
	public void setUp() {
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		studentDao = (StudentDao) ctx.getBean("studentDAO");
		System.out.println("Set Up");
	}
	
	@After
	public void clear() {
		ctx = null;
		System.out.println("CLear");
	}
	
	 @Test
     public void testQuery() {
        List<Student> students = studentDao.query();
        for (Student student : students) {
            System.out.println("id:" + student.getId()
                    + " , name:" + student.getName()
                    + ", age:" + student.getAge());
        }

    }

    @Test
    public void testSave() {

        Student student = new Student();
        student.setName("test");
        student.setAge(90);

        studentDao.save(student);
    }
}
