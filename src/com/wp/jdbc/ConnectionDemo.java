package com.wp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class ConnectionDemo{
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/";
	private static final String DBPASS = "root";

	public static Connection getConn() {
		return Conn;
	}

	public static void setConn(Connection conn) {
		Conn = conn;
	}

	private static final String DBUSER = "root";
	private static final String DBNAME = "employee";
	private static Statement st;
	private static Connection Conn;

	public static void ConnectDB() {
		try {
			Class.forName(DRIVER);
			Conn = DriverManager.getConnection(DBURL + DBNAME, DBUSER, DBPASS);
			System.out.println("Connected");
			st = Conn.createStatement();
		} catch (Exception ex) {
		
		}
	}

	public static void create(String query) {
		try {
			if (st == null) {
				ConnectDB();
			}
			st.execute(query);
		} catch (Exception ex) {
		
		}
	}

	public static void close() {
		try {

			Conn.close();

		} catch (Exception ex) {
	
		}

	}
}