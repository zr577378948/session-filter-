package com.zr.dao;

import java.util.List;

import com.zr.entity.User;

public interface UserDao {

	/**
	 * µÇÂ½²éÑ¯
	 */
	public User findByName(String uname);
}
