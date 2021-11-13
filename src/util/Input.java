package util;
import java.util.*;

import models.Activity;
import models.Brand;
import models.Customer;
import models.Reward;

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
			sc.nextLine();
			System.out.println("A: Brand Name");

			brandObj.setBrandName(sc.nextLine());

			System.out.println("B: Contact Number");

			brandObj.setContactNumber(sc.nextInt());
			sc.nextLine();
			System.out.println("C: Address");

			brandObj.setAddress(sc.nextLine());

			System.out.println("D: User Name");
			//sc.nextLine();
			brandObj.setUserName(sc.nextLine());

			System.out.println("E: Password");
			//sc.nextLine();
			brandObj.setPassWord(sc.nextLine());

			System.out.println("F: Confirm Password");
			//sc.nextLine();
			String passwd = sc.nextLine();

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
			DisplayOptions.getSc().next();
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
			sc.nextLine();
			custObj.setCustomerName(sc.nextLine());

			System.out.println("B: Contact Number");

			custObj.setContactNumber(sc.nextLine());

			System.out.println("C: Address");
			//sc.nextLine();
			custObj.setAddress(sc.nextLine());

			System.out.println("D: User Name");
			//sc.nextLine();
			custObj.setUserName(sc.nextLine());
			//sc.nextLine();
			System.out.println("E: Password");

			custObj.setPassWord(sc.nextLine());

			System.out.println("F: Confirm Password");
			//sc.nextLine();
			String passwd = sc.nextLine();

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
			DisplayOptions.getSc().next();
			return false;
		}
		return true;

	}

	public static boolean takeActivityInput(Activity actObj)
	{
		try
		{
			System.out.println("Enter activity details !!!!!");

			Scanner sc = DisplayOptions.getSc();
			sc.nextLine();
			System.out.println("A: Activity Name");

			actObj.setActivityName(sc.nextLine());

			System.out.println("B: Activity Code");

			actObj.setActivityCode(sc.nextLine());

			if(null == actObj.getActivityName() || "".equals(actObj.getActivityName())
					|| null == actObj.getActivityCode() || "".equals(actObj.getActivityCode()))
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

	public static boolean takeRewardInput(Reward rewObj)
	{
		try
		{
			System.out.println("Enter Reward details:");

			Scanner sc = DisplayOptions.getSc();

			System.out.println("A: Reward Name");
			sc.nextLine();
			rewObj.setRewardName(sc.nextLine());

			System.out.println("B: Reward Code");

			rewObj.setRewardCode(sc.next());

			if(null == rewObj.getRewardName() || "".equals(rewObj.getRewardName())
					|| null == rewObj.getRewardCode() || "".equals(rewObj.getRewardCode()))
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


}
