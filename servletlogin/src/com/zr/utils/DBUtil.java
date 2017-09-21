package com.zr.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * ����ģʽ��һ��ʵ��������ʹ��
 * new Emp();
 * new EMp();
 * new EMp();
 * @author Administrator
 *
 */
public class DBUtil {
	private static Connection conn =null;
	public static Connection getConnection(){
		//��Щ������ִ����֮��ر������Ӷ�����ô��Ȼ����Ϊ�գ����Ǳ��رյ����Ӷ��󲻿�����
		try {
			//���connection����Ϊnull���ߵ�ǰ�����Դ�Ѿ����رգ���ô�����´���һ������
			if(conn==null||conn.isClosed()){
				try {
					//1-��������
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//2-��������
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
	 * ��װ���ǶԱ����ݲ��������з���
	 * Object...args�ɱ���������������1�����߶�����ϵĲ���
	 * @return
	 */
	public static boolean update(String sql,Object...args){
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		int rowcount = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				//Ԥ����sql�����ÿһ��?ռλ����ֵ
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
