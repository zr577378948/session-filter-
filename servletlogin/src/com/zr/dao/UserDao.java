package com.zr.dao;

import java.util.List;

import com.zr.entity.User;

public interface UserDao {

	/**
	 * ��½��ѯ
	 */
	public User findByName(String uname);
}
