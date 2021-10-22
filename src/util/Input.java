package util;
import java.util.*;

import models.Brand;
import models.Customer;

public class Input {
	
	public static boolean takeLoginInput(Login login)
	{
		try
		{
			System.out.println("Enter login details !!!!!");
			
			Scanner sc = DisplayOptions.getSc();
			
			System.out.println("A: User Name");
			
			login.setUserName(sc.next());
			
			System.out.println("B: Password");
			
			login.setPassWord(sc.next());
			
		}
		catch(Exception e)
		{
			System.out.println("Invalid Input !!!!");
			return false;
		}
		return true;
		
	}
	
	public static boolean takeSignupInput(SignUp signUp)
	{
		try
		{
			System.out.println("Enter signup details !!!!!");
			
			Scanner sc = DisplayOptions.getSc();
			
			System.out.println("A: User Name");
			
			signUp.setUserName(sc.next());
			
			System.out.println("B: Password");
			
			signUp.setPassWord(sc.next());
			
			System.out.println("C: Confirm Password");
			
			String passwd = sc.next();
			
			if(!signUp.getPassWord().equals(passwd))
			{
				System.out.println("Invalid Input !!!!");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Invalid Input !!!!");
			return false;
		}
		return true;
		
	}
	
	
	public static void takeBrandInput(Brand brandObj)
	{
		Scanner sc = DisplayOptions.getSc();
		
		System.out.println("Enter brand name");
		
		brandObj.setName(sc.next());
		
		System.out.println("Enter brand address");
		
		brandObj.setAddress(sc.next());
		
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		brandObj.setJoinDate(date);
		
		return;
		
	}
	
	public static void takeCustomerInput(Customer customerObj)
	{
		Scanner sc = DisplayOptions.getSc();
		
		System.out.println("Enter customer name");
		
		customerObj.setName(sc.next());
		
		System.out.println("Enter customer address");
		
		customerObj.setAddress(sc.next());
		
		System.out.println("Enter phone number");
		
		customerObj.setPhoneNumber(sc.nextInt());
		
		return;
		
	}
}
