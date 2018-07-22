package com.springdata.cao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springdata.cao.domain.Employee;

/**
 * 可以为业务接口添加住家代替继承Spring的Repository接口，即：
 * @RepositoryDefinition(domainClass=Employee.class, idClass=Integer.class)
 * */
public interface EmployeeRepository extends Repository<Employee, Integer>{

	public Employee findByName(String name);
	
	// where name like ?% and age < ?
	public List<Employee> findByNameStartingWithAndAgeLessThan(String name, Integer age);
	
	//where name like %? and age < ?
	public List<Employee> findByNameEndingWithAndAgeLessThan(String name, Integer age);
	
	//where name in (?,?,  。。。) or age < ?
	public List<Employee> findByNameInOrAgeLessThan(List<String> nameList, Integer age);
	
	//where name in (?,?,  。。。) and age < ?
	public List<Employee> findByNameInAndAgeLessThan(List<String> nameList, Integer age);
	
	
	/**
	 * 【重要】
	 * 下面query中的Employee是实体类名，而不是表名。
	 * 这里是通过类名查询数据
	 * */
	@Query("select e from Employee e where e.id = (select max(o.id) from Employee o)")
	public Employee getEmployeeByMaxId();
	
	/**
	 * 根据索引（占位符）参数查询数据
	 * */
	@Query("select e from Employee e where e.name=?1 and e.age=?2")
	public List<Employee> getEmployeeByParms1(String name, Integer age);
	
	/**
	 * 根据命名参数查询数据
	 * */
	@Query("select e from Employee e where e.name=:name and e.age=:age")
	public List<Employee> findEmployeeByParams2(@Param("name") String name, @Param("age") Integer age);
	
	@Query("select e from Employee e where e.name like %?1%")
	public List<Employee> findEmployeeByNameLike1(String name);
	
	@Query("select e from Employee e where e.name like %:name%")
	public List<Employee> findEmployeeByNameLike2(@Param("name") String name);
	
	
	
}
