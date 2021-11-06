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
			
			
			if(login.getUserName() == null || login.getPassWord() == null 
					|| login.getUserName().equals("") || login.getPassWord().equals(""))
			{
				System.out.println("Invalid Input, username or password can't be null");
				return false;
			}
			else
			{
				return true;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Invalid Input !!!!");
			return false;
		}
		
	}
	
	public static boolean takeBrandSignupInput(Brand brandObj)
	{
		try
		{
			System.out.println("Enter brand signup details !!!!!");
			
			Scanner sc = DisplayOptions.getSc();
			
			System.out.println("A: Brand Name");
			
			brandObj.setBrandName(sc.next());
			
			System.out.println("B: Contact Number");
			
			brandObj.setContactNumber(sc.nextInt());
			
			System.out.println("C: Address");
			
			brandObj.setAddress(sc.next());
			
			System.out.println("D: User Name");
			
			brandObj.setUserName(sc.next());
			
			System.out.println("E: Password");
			
			brandObj.setPassWord(sc.next());
			
			System.out.println("F: Confirm Password");
			
			String passwd = sc.next();
			
			if(brandObj.getUserName() == null || brandObj.getUserName().equals("") 
					|| brandObj.getPassWord() == null || brandObj.getPassWord().equals(""))
			{
				System.out.println("Invalid Input, username or password can't be null !!!!");
				return false;
			}
			if(!brandObj.getPassWord().equals(passwd))
			{
				System.out.println("Passwords don't match, try again !!!!");
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
	
	public static boolean takeCustomerSignupInput(Customer custObj)
	{
		try
		{
			System.out.println("Enter customer signup details !!!!!");
			
			Scanner sc = DisplayOptions.getSc();
			
			System.out.println("A: Customer Name");
			
			custObj.setCustomerName(sc.next());
			
			System.out.println("B: Contact Number");
			
			custObj.setContactNumber(sc.nextInt());
			
			System.out.println("C: Address");
			
			custObj.setAddress(sc.next());
			
			System.out.println("D: User Name");
			
			custObj.setUserName(sc.next());
			
			System.out.println("E: Password");
			
			custObj.setPassWord(sc.next());
			
			System.out.println("F: Confirm Password");
			
			String passwd = sc.next();
			
			if(custObj.getUserName() == null || custObj.getUserName().equals("") 
					|| custObj.getPassWord() == null || custObj.getPassWord().equals(""))
			{
				System.out.println("Invalid Input, username or password can't be null !!!!");
				return false;
			}
			if(!custObj.getPassWord().equals(passwd))
			{
				System.out.println("Passwords don't match, try again !!!!");
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


}
