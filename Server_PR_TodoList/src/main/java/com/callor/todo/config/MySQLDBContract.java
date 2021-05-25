package com.callor.todo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBContract {

	private static Connection dbConn;

	static {
		
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/todoListDB";
		String username = "tdUser";
		String password = "tdUser"; 
		
		try {
			Class.forName(jdbcDriver);
			if (dbConn == null) {
				dbConn=DriverManager.getConnection(url, username, password);
				
			}
			System.out.println("Mysql 접속완료");
		
		} catch (ClassNotFoundException e) {
			System.out.println(" jdbcDriver를 찾을수 없음!! ");
		
		} catch (SQLException e) {
			System.out.println("접속불가 접속정보를 확인해주세요");
			System.out.println("=".repeat(40));
			System.out.println("jdbcDriver " +jdbcDriver );
			System.out.println("url : " +url);
			System.out.println("username" +username);
			System.out.println("password " + password );
			System.out.println("=".repeat(40));
		}
	}
	public static Connection getConnection() {
		
		return dbConn;
	}
}
