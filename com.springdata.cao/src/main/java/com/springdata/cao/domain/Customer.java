package com.springdata.cao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 用户：先开发实体类，再生产数据库
 * */
@Entity
public class Customer {
	private Integer id;
	private String name;
	private String age;
	
	@GeneratedValue
	@Id
	@Column(length=20)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}
