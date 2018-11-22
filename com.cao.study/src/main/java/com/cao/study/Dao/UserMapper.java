package com.cao.study.Dao;

import com.cao.study.Domain.User;

public interface UserMapper {
	int insert(User record);
    
    int insertSelective(User record);
    User checkLogin(User user);
}
