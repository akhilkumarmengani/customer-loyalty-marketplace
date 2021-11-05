package db;

import java.sql.*;

import models.Brand;
import models.Customer;
import models.RegularLoyaltyProgram;
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
	
	public static void insertRegularLoyaltyData(String query, RegularLoyaltyProgram regularLPObj) throws Exception
	{
		try
		{
			
			
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, regularLPObj.getBrandId());
			
			stmt.executeUpdate();
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			
			throw new Exception(e);
		}
	}
	
	public static void insertIntoCustomersToBrands(int customerId, int brandId) throws Exception {
		String query = "insert into customers_to_brands(customer_id,brand_id)  values (?,?)";
		try
		{
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, String.valueOf(customerId));
			
			stmt.setString(2,  String.valueOf(brandId));
			
			stmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			
			throw new Exception(e);
		}
	}			
	
	
	public static ResultSet getAllBrandLoyaltyPrograms() throws Exception{
		String query = "select * from brands";
		
		rs = stmt.executeQuery();

		return rs;
	
	
	}	
//	
//	public static void insertBrand(String query, Brand brand)
//	{
//		try
//		{
//			stmt = conn.prepareStatement(query);
//			
//			stmt.setLong(1, brand.getBrandId());
//			
//			stmt.setString(2, brand.getName());
//			
//			stmt.setString(3, brand.getAddress());
//			
//			stmt.setDate(4, brand.getJoinDate());
//			
//			stmt.executeUpdate();
//			
//		}
//		catch(Exception e)
//		{
//			return;
//		}
//	}
//	
//	public static void insertCustomer(String query, Customer custObj)
//	{
//		try
//		{
//			stmt = conn.prepareStatement(query);
//			
//			stmt.setInt(1, custObj.getCustomerId());
//			
//			stmt.setString(2, custObj.getName());
//			
//			stmt.setString(3, custObj.getAddress());
//			
//			stmt.setInt(4, custObj.getPhoneNumber());
//			
//			stmt.executeUpdate();
//			
//		}
//		catch(Exception e)
//		{
//			return;
//		}
//	}
}
