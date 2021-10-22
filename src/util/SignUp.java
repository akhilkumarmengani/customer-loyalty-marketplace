package util;
import db.DBTasks;
import models.Brand;
import models.Customer;

public class SignUp {
	

	
	public void takeInput()
	{
		int type;
	
		while(true)
		{
			System.out.println("Enter 1 for brand, any other input will be considered as customer");
			
			type = DisplayOptions.getSc().nextInt();
			
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
