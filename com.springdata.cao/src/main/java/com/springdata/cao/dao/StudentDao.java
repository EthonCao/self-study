package com.springdata.cao.dao;

import java.util.List;

import com.springdata.cao.domain.Student;

public interface StudentDao {
	/**
     * 查询所有学生
     * @return 所有学生
     */
    public List<Student> query();

    /**
     * 添加一个学生
     * @param student 待添加的学生
     */
    public void save(Student student);
}
