package customers;

import util.DisplayOptions;

public class CustomerLanding {
	
	public void takeInput()
	{
		while(true)
		{
			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.CustomerLanding));

			int option = DisplayOptions.getSc().nextInt();
			
			if(option < 1 || option > 5)
			{
				System.out.println("Invalid Option"); continue;
			}
			
			switch(option)
			{
				case 1:
				{
					
				}
				case 2:
				{
					
				}
				case 3:
				{
					
				}
				case 4:
				{
					
				}
				case 5:
				{
					
				}
				
			}
		}
	}
}
