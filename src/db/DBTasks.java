package db;

import java.sql.*;

import models.Brand;
import models.Customer;
import util.Login;
import util.SignUp;

public class DBTasks {
	
	public static final String jdbcURL 
	= "jdbc:oracle:thin:@csc540db.cxddnez16iql.us-east-2.rds.amazonaws.com:1521:DATABASE";
	
	public static final String userName = "nitw";
	
	public static final String passWord = "nitw1234";
	
	public static Connection conn;
	
    public static PreparedStatement stmt;
    
    public static ResultSet rs;

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		DBTasks.conn = conn;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		DBTasks.rs = rs;
	}

	public static String getJdbcurl() {
		return jdbcURL;
	}

	public static String getUsername() {
		return userName;
	}

	public static String getPassword() {
		return passWord;
	}
	
	public static ResultSet executeQueryForLogin(String query, Login login)
	{
		try
		{
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, login.getUserName());
			
			stmt.setString(2, login.getPassWord());
		
			rs = stmt.executeQuery();
			
			return rs;
			
		}
		catch(Exception e)
		{
			return  null;
		}
	}
	
	public static void insertLoginData(String query, SignUp signUp)
	{
		try
		{
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, signUp.getUserName());
			
			stmt.setString(2, signUp.getPassWord());
			
			stmt.setString(3, signUp.getLoginType());
			
			System.out.println(stmt);
			
			stmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			
			return;
		}
		
	}
	
	public static void insertBrand(String query, Brand brand)
	{
		try
		{
			stmt = conn.prepareStatement(query);
			
			stmt.setLong(1, brand.getBrandId());
			
			stmt.setString(2, brand.getName());
			
			stmt.setString(3, brand.getAddress());
			
			stmt.setDate(4, brand.getJoinDate());
			
			stmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			return;
		}
	}
	
	public static void insertCustomer(String query, Customer custObj)
	{
		try
		{
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, custObj.getCustomerId());
			
			stmt.setString(2, custObj.getName());
			
			stmt.setString(3, custObj.getAddress());
			
			stmt.setInt(4, custObj.getPhoneNumber());
			
			stmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			return;
		}
	}
}
