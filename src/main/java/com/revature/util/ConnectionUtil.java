package com.revature.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	private static Connection conn = null;
	
	private ConnectionUtil() {
	}
	
	public static Connection getConnection() {
		//Check if there is a connection instance
		//if there is then return it
		
		try {
			if(conn != null && !conn.isClosed()) {
				System.out.println("Using a previously made connection");
				return conn;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		//if not we'll set one up
		
		//this is the not secure method
		
		//this will change with cloud services
//		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=bank";
//		String username = "postgres";
//		String password = "postgres";
//		
//		try {
//			conn = DriverManager.getConnection(url, username, password);
//			System.out.println("Established connection to database!");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Cannot establish connection");
//			e.printStackTrace();
//		}
//		
//		String url = "";
//		String username = "";
//		String password = "";
//		
//		Properties prop = new Properties();
		
//		try {
//			
//			prop.load(new FileReader("C:\\Users\\ekajl\\Documents\\workspace-spring-tool-suite-4-4.14.1.RELEASE\\JDBCDemo\\src\\main\\resources\\application.properties"));
//			
//			url = prop.getProperty("url");
//			username = prop.getProperty("username");
//			password = prop.getProperty("password");
//			
//			conn = DriverManager.getConnection(url, username, password);
//			System.out.println("Established connection to database!");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Cannot establish connection");
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			System.out.println("Cannot find properties file");
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("Something's wrong with the properties file");
//			e.printStackTrace();
//		}
//		
		
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Established connection to database!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot establish connection");
			e.printStackTrace();
		}
		
		
		
		return conn;
	}

}