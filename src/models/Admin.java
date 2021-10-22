package models;

import db.DBTasks;
import util.DisplayOptions;
import util.Input;
import java.util.*;

public class Admin {
	
	public void takeInput()
	{
		int type;
	
		while(true)
		{
			List<String> adminOptions = DisplayOptions.options.get("Login-Admin");
			
			DisplayOptions.printOptions(adminOptions);
			
			System.out.println("Enter the choice");
			
			type = DisplayOptions.getSc().nextInt();
			
			if(type > adminOptions.size()) 
			{
				System.out.println("Invalid input !!!!");
				continue;
			}
			
			if(type == 1)
			{
				Brand brandObj = new Brand();
				try
				{
					Input.takeBrandInput(brandObj);
				}
				catch(Exception e)
				{
					System.out.println("Invalid Input !!!!"); continue;
				}
				
				brandObj.setBrandId(100);
				
				String query = "insert into Brand values(BRAND_ID_SEQUENCE.nextval,?,?,?)";
				
				DBTasks.insertBrand(query, brandObj);
				
			}
			else
			{
				Customer custObj = new Customer();
				try
				{
					Input.takeCustomerInput(custObj);
				}
				catch(Exception e)
				{
					System.out.println("Invalid Input !!!!"); continue;
				}
				
				custObj.setCustomerId(200);
				
				String query = "insert into Customer values(CUSTOMER_ID_SEQUENCE.nextval,?,?,?)";
				
				DBTasks.insertCustomer(query, custObj);
				
				
			}
		}
		
	}
}
