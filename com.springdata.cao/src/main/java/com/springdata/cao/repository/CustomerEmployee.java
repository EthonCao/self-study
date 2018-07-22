package com.springdata.cao.repository;

import org.springframework.data.repository.Repository;

import com.springdata.cao.domain.Customer;

public interface CustomerEmployee extends Repository<Customer, Integer>{
	public Customer findByName(String name);
}
