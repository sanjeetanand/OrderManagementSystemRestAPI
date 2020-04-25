package com.psl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private final String driver = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/order_db?useSSL=false";
	private final String user = "root";
	private final String password = "sanjeet";
	
	private Connection con = null;
	
	public Connection getConnection() {
		try {
			if(con == null || con.isClosed()) {
				Class.forName(driver);
				con = DriverManager.getConnection(url,user,password);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void closeConnection() {
		try {
			if(con != null && !con.isClosed()) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			
		}
	}
	
	public static void main(String[] args) {
		DBManager db = new DBManager();
		System.out.println(db.getConnection());
		db.closeConnection();
		
	}
}
