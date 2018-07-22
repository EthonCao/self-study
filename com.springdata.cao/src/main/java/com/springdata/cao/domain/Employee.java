package com.springdata.cao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 雇员: 先开发实体类，然后自动生成数据表
 */
@Entity
public class Employee {
	 
	private Integer id;
	private String name;
	private Integer age;
	
	/**
	 * 为id加入注解：
	 * @id : 告诉EntityManagerFactory, 这是一个id
	 * @GeneratedValue:Id的值是自增
	 * */
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	 
	 
}
