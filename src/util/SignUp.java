package util;
import customers.CustomerSignUp;
import models.BrandSignUp;


public class SignUp {
	
	
	
	public void takeInput()
	{

		while(true)
		{
			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.SignUp));
			
			
			int option = DisplayOptions.getSc().nextInt();
			
			switch(option)
			{
				case 1: 
				{	boolean flag = new BrandSignUp().takeInput(); 
					if(flag==false) return;
					break;
				}
				case 2: 
				{	boolean flag = new CustomerSignUp().takeInput(); 
					if(flag==false) return;
					break;
				}				
				case 3: {return;}
				default: {System.out.println("Invalid input !!!"); continue;}
			}
			
		}
		
	}

}
