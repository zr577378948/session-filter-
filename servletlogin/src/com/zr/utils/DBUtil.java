package com.zr.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 单例模式：一个实例到处被使用
 * new Emp();
 * new EMp();
 * new EMp();
 * @author Administrator
 *
 */
public class DBUtil {
	private static Connection conn =null;
	public static Connection getConnection(){
		//有些操作在执行完之后关闭了连接对象，那么虽然对象不为空，但是被关闭的连接对象不可用了
		try {
			//如果connection对象为null或者当前这个资源已经被关闭，那么就重新创建一个连接
			if(conn==null||conn.isClosed()){
				try {
					//1-加载驱动
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//2-创建连接
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
							, "SCOTT", "TIGER");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void close(Connection conn,Statement statement,ResultSet rSet){
		try {
			if(conn!=null){
				conn.close();
			}
			if(statement!=null){
				statement.close();
			}
			if(rSet!=null){
				rSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 封装的是对表数据操作的所有方法
	 * Object...args可变参数，代表可以有1个或者多个以上的参数
	 * @return
	 */
	public static boolean update(String sql,Object...args){
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		int rowcount = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				//预编译sql语句中每一个?占位符赋值
				pStatement.setObject(i+1, args[i]);
			}
			//insert into emp values();
			rowcount = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(connection, pStatement, null);
		}
		return rowcount==0?false:true;
	}

}
