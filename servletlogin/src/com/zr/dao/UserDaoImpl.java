package com.zr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.entity.User;
import com.zr.utils.DBUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public User findByName(String uname) {
		User user=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from user_table where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			rs = ps.executeQuery();
			while(rs.next()){
				String name = rs.getString(1);
				String password = rs.getString(2);
				user =  new User(name, password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
