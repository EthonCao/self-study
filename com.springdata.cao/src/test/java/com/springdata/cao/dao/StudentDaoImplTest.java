package com.springdata.cao.dao;

import java.util.List;

import org.junit.Test;

import com.springdata.cao.domain.Student;

public class StudentDaoImplTest {

	 @Test
     public void testQuery() {
        StudentDao studentDAO = new StudentDaoImpl();
        List<Student> students = studentDAO.query();

        for (Student student : students) {
            System.out.println("id:" + student.getId()
                    + " , name:" + student.getName()
                    + ", age:" + student.getAge());
        }

    }

    @Test
    public void testSave() {

    	StudentDao studentDAO = new StudentDaoImpl();
        Student student = new Student();
        student.setName("test");
        student.setAge(30);

        studentDAO.save(student);
    }
}
