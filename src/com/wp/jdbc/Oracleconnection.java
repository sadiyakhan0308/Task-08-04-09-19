package com.wp.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
public class Oracleconnection {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DBPASS = "abcd1234";
	private static final String DBUSER = "sadiya";

	private static Connection Conn;
	
	public static Connection getConn() {
		return Conn;
	}

	public static void setConn(Connection conn) {
		Conn = conn;
	}

	public static void ConnectDB() {
		try {
			Class.forName(DRIVER);
			Conn = DriverManager.getConnection(DBURL , DBUSER, DBPASS);
	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void close() {
		try{
				Conn.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		
		}
     
}
}
