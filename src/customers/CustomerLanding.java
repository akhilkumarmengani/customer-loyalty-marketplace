package customers;

import util.AppData;
import util.DisplayOptions;
import java.sql.Date;
import java.util.List;


public class CustomerLanding {
	
	public void takeInput() throws Exception
	{
		
		while(true)
		{

			System.out.println("--------------Customer Landing--------------");
			System.out.println();
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
					System.out.println("--------------Enroll in Loyalty Program--------------");
					System.out.println();
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
									break;
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
					break;
				}
				case 2:
				{
					//CustomerLandingUtil.getRewardActivities();	
					// Display All loyalty Programs
					System.out.println("--------------Reward Activities--------------");
					System.out.println();

					System.out.println("Select One from the list of Loyalty Programs :");
					
					int brandId = CustomerLandingUtil.displayBrandLoyaltyPrograms(AppData.customerId);
					
					if(brandId == -1) {
						break;
					}
					
					
					AppData.brandId = brandId;
					
					List<String[]> activities = CustomerLandingUtil.getActivitiesForBrand(brandId);

					if(activities==null || activities.size()==0){
						System.out.println("No Activities for this Brand!!");
						return;					
					}
					
//					CustomerLandingUtil.displayActivities(activities);
					
//					for(int i = 0; i < activities.size(); i++) {
//						System.out.println((i+1)+". "+ activities.get(i)[1]);
//					}
					
					
					//DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.RewardActivities));
					while(true) {
						System.out.println("--------------Activity Selection--------------");
						System.out.println();
						for(int i = 0; i < activities.size(); i++) {
							System.out.println((i+1)+". "+ activities.get(i)[1]);
						}
						System.out.println((activities.size()+1)+". Exit");
						int actOption = DisplayOptions.getSc().nextInt();
						if(actOption==activities.size()+1){
							break;
						}
						if(!CustomerLandingUtil.validateInput(actOption,1,activities.size())) {
							System.out.println("Please Enter A Valid Input :|");
						}
						else {
							String activityCode = activities.get(actOption-1)[0];
							if("ACT101".equals(activityCode)) {
//								long millis = System.currentTimeMillis();
//						        Date date = new java.sql.Date(millis);
								

								while(true) {
									System.out.println("--------------Purchase Activity--------------");
									System.out.println();

									DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.Purchase));

									int purchaseOption = DisplayOptions.getSc().nextInt();
//									int amount = -1;

									if(!CustomerLandingUtil.validateInput(purchaseOption,1,2)){
										System.out.println("Please Enter A Valid Input!");
										continue;
									}


									if(purchaseOption==1) {
//										System.out.println("Enter the Purchase Amount:");
//										int purchaseAmount = DisplayOptions.getSc().nextInt();
										System.out.println("Select the Gift Card:");
										boolean useGiftCard = false;
										int giftCardValue = 0;
										int instances = 0;

										List<String[]> giftCards = CustomerLandingUtil.getAllGiftCardsOfCustomer(AppData.customerId,AppData.brandId);

										if( giftCards==null || giftCards.size()==0){
											System.out.println("You don't have any gift cards currently");
										}
										else{
											System.out.println("If you want to select a gift card, please choose from below options");
											System.out.println("Gift Cards Available:");

											System.out.println("Instances - "+ giftCards.get(0)[1] +" , GiftCard Value - "+ giftCards.get(0)[0]);

											System.out.println("Enter Yes / No :");

											String giftOption = DisplayOptions.getSc().next();

											if("Yes".equals(giftOption)) {
												giftCardValue = Integer.valueOf(giftCards.get(0)[0]);
												instances = Integer.valueOf(giftCards.get(0)[1]);
												useGiftCard = true;
											}
										}

										CustomerLandingUtil.insertIntoCustomerToBLPActivities(AppData.customerId,AppData.brandId,activityCode,
												giftCardValue, instances);
										break;
									}
									else {
										break;
									}
								}
							}
							else if("ACT102".equals(activityCode)) {

								System.out.println("--------------Leave A Review Activity--------------");
								System.out.println();


								while(true){

									DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.LeaveAReview));
									int reviewOption = DisplayOptions.getSc().nextInt();

									if(reviewOption <1 || reviewOption>2){
										System.out.println("Enter Correct Option!!");
										continue;
									}


									if(reviewOption == 1){
										CustomerLandingUtil.insertIntoCustomerToBLPActivitiesForReview(AppData.customerId,AppData.brandId,activityCode);
									}
									else{
										break;
									}


								}
							}
							else if("ACT103".equals(activityCode)) {
								while(true){
									System.out.println("--------------Leave A Review Activity--------------");
									System.out.println();

									DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.ReferAFriend));

									int referOption = DisplayOptions.getSc().nextInt();

									if(referOption <1 || referOption>2){
										System.out.println("Enter Correct Option!!");
										continue;
									}



									if(referOption == 1){
										CustomerLandingUtil.insertIntoCustomerToBLPActivitiesForReview(AppData.customerId,AppData.brandId,activityCode);
									}
									else{
										break;
									}


								}
							}
							else {
								break;
							}
						}
					}
					break;
				}
				case 3:
				{
					//CustomerLandingUtil.viewWallet();
					System.out.println("-------------- View Wallet --------------");
					System.out.println();
					CustomerLandingUtil.displayWallet(AppData.customerId);

					while(true) {

						System.out.println();
						DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.GoBack));
						int vwOption = DisplayOptions.getSc().nextInt();
						if(vwOption==1){
							break;
						}
						System.out.println("Enter 1 to Go Back!!");
					}
					break;
				}
				case 4:
				{
					//CustomerLandingUtil.redeemPoints();
					while(true) {
							System.out.println("-------------- Redeem Points --------------");
							System.out.println();
							DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.RedeemPoints));
							int redeem_option = DisplayOptions.getSc().nextInt();
							if(redeem_option < 1 || redeem_option > 2)
							{
								System.out.println("Incorrect option  !!"); continue;
							}
							if(redeem_option == 2)
							{
								break ;
							}

							int brandId = CustomerLandingUtil.displayBrandLoyaltyPrograms(AppData.customerId);

							if(brandId == -1) {

								continue;
							}

							AppData.brandId = brandId;

							int total_wallet_points = CustomerLandingUtil.getTotalWalletPointsForACustomerInBrand(brandId,AppData.customerId);

							List<String[]> rewOptions = CustomerLandingUtil.getAllCurrentRewardsForABrand(brandId);

							if(rewOptions == null || rewOptions.size() == 0)
							{
								System.out.println("There are no rewards present for the selected" +
										" brand, please select another brand. ");
								continue;
							}

							int selRewOption;

							int[] total_reward_instances = new int[2];
							int[] uid = new int[2];


							do {

								System.out.println("List of all rewards available for the selected brand :");

								for (int i = 0; i < rewOptions.size(); i++) {
									System.out.println((i+1)+". Reward Name :" + rewOptions.get(i)[3] +
											" | Instances :" + rewOptions.get(i)[1] + " | Value: " + rewOptions.get(i)[0]);
									uid[i] = Integer.parseInt(rewOptions.get(i)[4]);
								}

								System.out.println("Select a reward option: To exit, enter -1");

								selRewOption = DisplayOptions.getSc().nextInt();

								if(selRewOption==-1){
									break;
								}

								if (selRewOption > rewOptions.size()) {
									System.out.println("Incorrect reward option :");
									continue;
								}

								System.out.println("Select the number of instances of the reward");

								int no_of_instances = DisplayOptions.getSc().nextInt();

								int total_instances = Integer.parseInt(rewOptions.get(selRewOption-1)[1]);

								int rew_value = Integer.parseInt(rewOptions.get(selRewOption-1)[0]);

								String rewCode = rewOptions.get(selRewOption-1)[2];

								if(no_of_instances > total_instances)
								{
									System.out.println("Please enter number of rewards less than or equal to the total number of reward instances");
									continue;
								}

								if(total_wallet_points < no_of_instances * rew_value)
								{
									System.out.print("You dont have enough wallet points to claim the reward "); continue;
								}

								total_wallet_points = total_wallet_points - (no_of_instances * rew_value);

								if(rewCode.equals("REW101"))
								{
									total_reward_instances[0]+= no_of_instances;
								}
								else
								{
									total_reward_instances[1]+= no_of_instances;
								}

								rewOptions.get(selRewOption-1)[1] = String.valueOf(total_instances - no_of_instances);
							}while(selRewOption != -1);


							CustomerLandingUtil.updateCustomerRewardsForABrand(AppData.customerId,AppData.brandId,total_reward_instances, uid );

					}
					//break;
					return;
				}
				case 5:
				{
					return;
				}
				
			}
		}
	}
}
