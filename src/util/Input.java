package util;
import java.util.*;

import models.Brand;
import models.Customer;

public class Input {
	
	public static void takeLoginInput(Login login)
	{
		Scanner sc = DisplayOptions.getSc();
		
		System.out.println("A: User Id");
		
		login.setUserId(sc.next());
		
		System.out.println("B: Password");
		
		login.setPassWord(sc.next());
		
		return;
		
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
