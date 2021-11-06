package customers;

import util.DisplayOptions;


public class CustomerLanding {
	
	public void takeInput() throws Exception
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
					//CustomerLandingUtil.enrollInLoyaltyProgram();
					DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.Enroll));
					
					while(true) {
						option = DisplayOptions.getSc().nextInt();
						if(!CustomerLandingUtil.validateInput(option,1,2)) {
							System.out.println("Please Enter A Valid Input :|");
						}
						else {
							if(option==1) {
								String enrolledBrand = CustomerLandingUtil.enrollInLoyaltyProgram();
								if(enrolledBrand!=null) {
									System.out.println("You are Enrolled in "+enrolledBrand);
								}
								else {
									System.out.println("You Are Already Enrolled In This Loyalty");
								}
							}
							else {
								break;
							}
						}
					}
				}
				case 2:
				{
					//CustomerLandingUtil.getRewardActivities();	
					while(true) {
						option = DisplayOptions.getSc().nextInt();
						if(!CustomerLandingUtil.validateInput(option,1,4)) {
							System.out.println("Please Enter A Valid Input :|");
						}
						else {
							if(option==1) {
								
							}
							else if(option==2) {
								
							}
							else if(option==3) {
								
							}
							else {
								break;
							}
						}
					}
				}
				case 3:
				{
					//CustomerLandingUtil.viewWallet();
					while(true) {
						
					}
				}
				case 4:
				{
					//CustomerLandingUtil.redeemPoints();
					while(true) {
						
					}
				}
				case 5:
				{
					return;
				}
				
			}
		}
	}
}
