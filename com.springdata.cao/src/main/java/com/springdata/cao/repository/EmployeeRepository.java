package com.springdata.cao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springdata.cao.domain.Employee;

/**
 * ����Ϊҵ��ӿ����ס�Ҵ���̳�Spring��Repository�ӿڣ�����
 * @RepositoryDefinition(domainClass=Employee.class, idClass=Integer.class)
 * */
public interface EmployeeRepository extends Repository<Employee, Integer>{

	public Employee findByName(String name);
	
	// where name like ?% and age < ?
	public List<Employee> findByNameStartingWithAndAgeLessThan(String name, Integer age);
	
	//where name like %? and age < ?
	public List<Employee> findByNameEndingWithAndAgeLessThan(String name, Integer age);
	
	//where name in (?,?,  ������) or age < ?
	public List<Employee> findByNameInOrAgeLessThan(List<String> nameList, Integer age);
	
	//where name in (?,?,  ������) and age < ?
	public List<Employee> findByNameInAndAgeLessThan(List<String> nameList, Integer age);
	
	
	/**
	 * ����Ҫ��
	 * ����query�е�Employee��ʵ�������������Ǳ�����
	 * ������ͨ��������ѯ����
	 * */
	@Query("select e from Employee e where e.id = (select max(o.id) from Employee o)")
	public Employee getEmployeeByMaxId();
	
	/**
	 * ����������ռλ����������ѯ����
	 * */
	@Query("select e from Employee e where e.name=?1 and e.age=?2")
	public List<Employee> getEmployeeByParms1(String name, Integer age);
	
	/**
	 * ��������������ѯ����
	 * */
	@Query("select e from Employee e where e.name=:name and e.age=:age")
	public List<Employee> findEmployeeByParams2(@Param("name") String name, @Param("age") Integer age);
	
	@Query("select e from Employee e where e.name like %?1%")
	public List<Employee> findEmployeeByNameLike1(String name);
	
	@Query("select e from Employee e where e.name like %:name%")
	public List<Employee> findEmployeeByNameLike2(@Param("name") String name);
	
	
	
}
