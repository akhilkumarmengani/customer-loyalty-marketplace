package db;

import java.sql.*;

import models.Brand;
import models.Customer;
import util.Login;

public class DBTasks {
	
	public static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
	
	public static final String userName = "smulkur";
	
	public static final String passWord = "abcd1234";
	
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
	
	public static void insertBrandLoginData(String query, Brand brandObj) throws Exception
	{
		try
		{
			stmt = conn.prepareStatement(query);
			

			stmt.setString(1, brandObj.getUserName());
			
			stmt.setString(2, brandObj.getPassWord());
			
			stmt.setString(3, "BRAND");
			
			stmt.setString(4, brandObj.getBrandName());
			
			stmt.setString(5, brandObj.getAddress());
			
			stmt.setInt(6, brandObj.getContactNumber());

			stmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			
			throw new Exception(e);
		}
		
	}
	
	public static void insertCustomerLoginData(String query, Customer custObj) throws Exception
	{
		try
		{
			stmt = conn.prepareStatement(query);
			

			stmt.setString(1, custObj.getUserName());
			
			stmt.setString(2, custObj.getPassWord());
			
			stmt.setString(3, "CUSTOMER");
			
			stmt.setString(4, custObj.getCustomerName());
			
			stmt.setString(5, custObj.getAddress());
			
			stmt.setInt(6, custObj.getContactNumber());

			stmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			
			throw new Exception(e);
		}
		
	}

	public static Brand getBrandInfo(String userName)
	{
		try
		{
			String query = "select name, address, contact_number from users where user_name = ?";
			
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, userName);
			
			rs = stmt.executeQuery();
			
			if(rs == null || !rs.next())
			{
				System.out.println("Corresponding brand is not present !!!");
				
				return null;
			}
			
			Brand brandObj = new Brand();
			
			brandObj.setBrandName(rs.getString("name"));
			
			brandObj.setAddress(rs.getString("address"));
			
			brandObj.setContactNumber(rs.getInt("contact_number"));
			
			return brandObj;
			
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static Customer getCustomerInfo(String userName)
	{
		try
		{
			String query = "select name, address, contact_number from users where user_name = ?";
			
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, userName);
			
			rs = stmt.executeQuery();
			
			if(rs == null || !rs.next())
			{
				System.out.println("Corresponding customer is not present !!!");
				
				return null;
			}
			
			Customer custObj = new Customer();
			
			custObj.setCustomerName(rs.getString("name"));
			
			custObj.setAddress(rs.getString("address"));
			
			custObj.setContactNumber(rs.getInt("contact_number"));
			
			return custObj;
			
		}
		catch(Exception e)
		{
			return null;
		}
	}


}
