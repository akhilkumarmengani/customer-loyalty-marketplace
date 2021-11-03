package models;

import db.DBTasks;
import util.DisplayOptions;
import util.Input;
import util.Login;

public class CustomerSignUp {
	
	public boolean takeInput()
	{
		Customer custObj;
		
		while(true)
		{
			custObj = new Customer();
			
			if(!Input.takeCustomerSignupInput(custObj)) continue;
			
			break;
			
		}	
		
		while(true)
		{
			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.BrandSignUp));

			int option = DisplayOptions.getSc().nextInt();
			
			if(option < 1 || option > 2)
			{
				System.out.println("Invalid Option"); continue;
			}
			if(option == 2) return true;
		
			try
			{
				String query = "insert into users(user_name, passwd, login_type, name, address, contact_number) "
						+ "values (?,?,?,?,?,?)";
					
				DBTasks.insertCustomerLoginData(query, custObj);
				
				System.out.println("Registered successfully !!!!1");
				
				new Login().takeInput();
				
				return false;
			
			}
			catch(Exception e)
			{
//				System.out.println("Username aldready exists !!!");
				continue;
			}
			
		}
	}
}
