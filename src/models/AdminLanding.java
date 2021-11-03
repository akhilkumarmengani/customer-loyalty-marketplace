package models;

import util.DisplayOptions;

public class AdminLanding {

	public void takeInput()
	{
		while(true)
		{
			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.AdminLanding));

			int option = DisplayOptions.getSc().nextInt();
			
			if(option < 1 || option > 7)
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
				case 6:
				{
					
				}
				case 7:
				{
					
				}
				
			}
		}
	}
}
