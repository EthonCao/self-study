package com.springdata.cao.dao;

import java.util.List;

import com.springdata.cao.domain.Student;

public interface StudentDao {
	/**
     * ��ѯ����ѧ��
     * @return ����ѧ��
     */
    public List<Student> query();

    /**
     * ���һ��ѧ��
     * @param student ����ӵ�ѧ��
     */
    public void save(Student student);
}
