package com.cao.study.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cao.study.Dao.UserMapper;
import com.cao.study.Domain.User;
import com.cao.study.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	   @Autowired
	   private UserMapper userMapper;
	 
		@Override
		public User checkLogin(User user) {
			// TODO Auto-generated method stub
			return null;
		}
}
