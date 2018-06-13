package com.springdata.cao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.springdata.cao.domain.Student;

/**
 * 通过Spring JDBC 访问数据库
 * */
public class StudentDaoSpringJdbcImpl implements StudentDao {

	private JdbcTemplate JdbcTemplate;
	
	@Override
	public List<Student> query() {
		final List<Student> students = new ArrayList<Student>();
		String sql = "select id, name , age from student";
		JdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet resultSet) throws SQLException {
				 Student student = null;
				 while (resultSet.next()) {
					 student = new Student();
					 student.setId(resultSet.getInt("id"));
					 student.setName(resultSet.getString("name"));
					 student.setAge(resultSet.getInt("age"));
					 
					 students.add(student);
				 }
			}
		} );
		return students;
	}

	@Override
	public void save(Student student) {
		String sql = "insert into student(name, age) values(?,?)";
		JdbcTemplate.update(sql, new Object[] {student.getName(), student.getAge()});
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return JdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		JdbcTemplate = jdbcTemplate;
	}
	
}
