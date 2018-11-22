package com.cao.study.Domain;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class User {
	 private String username;
	    
	    private Date birthday;
	 
	    private Integer id;
	 
	    private String sex;
	 
	    private String address;
	 
	    private String password;
	 
	    public String getUsername() {
	        return username;
	    }
	 
	    public void setUsername(String username) {
	        this.username = username == null ? null : username.trim();
	    }
	 
	    public Date getBirthday() {
	        return birthday;
	    }
	 
	    public void setBirthday(Date birthday) {
	        this.birthday = birthday;
	    }
	 
	    public Integer getId() {
	        return id;
	    }
	 
	    public void setId(Integer id) {
	        this.id = id;
	    }
	 
	    public String getSex() {
	        return sex;
	    }
	 
	    public void setSex(String sex) {
	        this.sex = sex == null ? null : sex.trim();
	    }
	 
	    public String getAddress() {
	        return address;
	    }
	 
	    public void setAddress(String address) {
	        this.address = address == null ? null : address.trim();
	    }
	 
	    public String getPassword() {
	        return password;
	    }
	 
	    public void setPassword(String password) {
	        this.password = password == null ? null : password.trim();
	    }
}
