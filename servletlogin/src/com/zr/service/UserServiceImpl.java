package com.zr.service;

import java.util.List;

import com.zr.dao.UserDao;
import com.zr.dao.UserDaoImpl;
import com.zr.entity.User;

public class UserServiceImpl implements UserService{

	@Override
	public User findByName(String uname) {
		UserDao dao = new UserDaoImpl();
		return dao.findByName(uname);
	}

}
