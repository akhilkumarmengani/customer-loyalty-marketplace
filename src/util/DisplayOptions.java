package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DisplayOptions {

	public static Map<String, List<String>> options = new HashMap<>();

	public static Scanner sc = new Scanner(System.in);

	public static Map<String, List<String>> getOptions() {
		return options;
	}

	public static void setOptions(Map<String, List<String>> options) {
		DisplayOptions.options = options;
	}

	public static Scanner getSc() {
		return sc;
	}

	public static void setSc(Scanner sc) {
		DisplayOptions.sc = sc;
	}

	public static final String Login = "Login";
	public static final String SignUp = "Sign-up";
	public static final String ShowQueries = "Show Queries";
	public static final String Exit = "Exit";
	public static final String Home = "Home";
	public static final String SignIn = "Sign-in";
	public static final String GoBack = "Go Back";
	public static final String UserType = "User Type";
	public static final String Brand = "Brand";
	public static final String BrandSignUp = "Brand Sign-up";
	public static final String CustomerSignUp = "Customer Sign-up";
	public static final String AddBrand = "Add brand";
	public static final String AddCustomer = "Add customer";
	public static final String ShowBrandInfo = "Show brand's info";
	public static final String ShowCustomerInfo = "Show customer's info";
	public static final String AddActivityType = "Add activity type";
	public static final String AddRewardType = "Add reward type";
	public static final String AdminLanding = "Admin: Landing";
	public static final String CustomerLanding = "Customer: Landing";
	public static final String Logout = "Logout";


	//---------------------Customer Strings Start--------------------------

	// Customer Landing Strings
	public static final String Enroll = "Enroll in Loyalty Program";
	public static final String RewardActivities = "Reward Activities";
	public static final String ViewWallet = "View Wallet";
	public static final String RedeemPoints = "Redeem Points";
	public static final String CustomerLogout = "Customer Logout";


	// Customer: Reward Activities
	public static final String Purchase = "Purchase";
	public static final String LeaveAReview = "Leave A Review";
	public static final String ReferAFriend = "Refer A Friend";

	//Customer: Redeem Points
	public static final String RewardsSelection  = "Rewards Selection";


	//---------------------Customer Strings End--------------------------

	// Brand : Strings
	public static final String BrandLanding = "Brand: Landing";

	public static final String AddLoyaltyProgram = "Add loyalty program";
	public static final String AddRERules = "Add RE Rules";
	public static final String UpdateRERule = "Update RE Rules";
	public static final String AddRRRules = "Add RR Rules";
	public static final String UpdateRRRule = "Update RR Rule";
	public static final String ValidateLoyaltyProgram = "Validate Loyalty Program";

	public static final String LoyaltyProgram = "Loyalty_Program";

	public static final String Regular = "Regular";
	public static final String Tier = "Tier";

	public static final String ActivityTypes = "Activity Types";
	public static final String RewardTypes = "Reward Types";

	public static final String LeaveReview = "Leave a review";
	public static final String ReferFriend = "Refer a friend";

	public static final String GiftCard = "Gift_Card";
	public static final String FreeProduct = "Free_Product";

	public static final String TiersSetup = "Tiers_Setup";

	public static final String SetUp = "Setup";

	public static final String Validate = "validate";


	public static void populateCustomerOptions() {
		// Customer Landing
		List<String> optionsList = new ArrayList<>();
		optionsList.addAll(Arrays.asList(Enroll,RewardActivities,ViewWallet,RedeemPoints,CustomerLogout));
		options.put(CustomerLanding, optionsList);


		//Customer: Enroll in Loyalty Program
		optionsList = new ArrayList<>();
		optionsList.addAll(Arrays.asList(Enroll,GoBack));
		options.put(Enroll, optionsList);


		//Customer: Reward Activities
		optionsList = new ArrayList<>();
		optionsList.addAll(Arrays.asList(Purchase,LeaveAReview,ReferAFriend,GoBack));
		options.put(RewardActivities, optionsList);

		//Customer: Purchase
		optionsList = new ArrayList<>();
		optionsList.addAll(Arrays.asList(Purchase,GoBack));
		options.put(Purchase, optionsList);

		//Customer: Leave a review
		optionsList = new ArrayList<>();
		optionsList.addAll(Arrays.asList(LeaveAReview,GoBack));
		options.put(LeaveAReview, optionsList);

		//Customer: Refer a friend
		optionsList = new ArrayList<>();
		optionsList.addAll(Arrays.asList(ReferAFriend,GoBack));
		options.put(ReferAFriend, optionsList);

		//Customer: View Wallet
		optionsList = new ArrayList<>();
		optionsList.addAll(Arrays.asList(GoBack));
		options.put(ViewWallet, optionsList);

		//Customer: Redeem Points
		optionsList = new ArrayList<>();
		optionsList.addAll(Arrays.asList(RewardsSelection,GoBack));
		options.put(RedeemPoints, optionsList);

		// Go Back
		optionsList = new ArrayList<>();
		optionsList.addAll(Arrays.asList(GoBack));
		options.put(GoBack, optionsList);


	}

	public static void populateOptions() {

		List<String> list;

//		list = new ArrayList<>();
//		list.add(Login);
//		list.add(SignUp);
//		list.add(ShowQueries);
//		list.add(Exit);

		list = new ArrayList<>();
		list.add(Login);
		list.add(SignUp);
		list.add(ShowQueries);
		list.add(Exit);

		options.put(Home, list);

		list = new ArrayList<>();
		list.add(SignIn);
		list.add(GoBack);

		options.put(Login, list);

		list = new ArrayList<>();
		list.add(BrandSignUp);
		list.add(CustomerSignUp);
		list.add(GoBack);

		options.put(SignUp, list);

		list = new ArrayList<>();
		list.add(SignUp);
		list.add(GoBack);

		options.put(BrandSignUp, list);

		list = new ArrayList<>();
		list.add(SignUp);
		list.add(GoBack);

		options.put(CustomerSignUp, list);

		list = new ArrayList<>();
		list.add(AddBrand);
		list.add(AddCustomer);
		list.add(ShowBrandInfo);
		list.add(ShowCustomerInfo);
		list.add(AddActivityType);
		list.add(AddRewardType);
		list.add(Logout);

		options.put(AdminLanding, list);

		list = new ArrayList<>();
		list.add(AddBrand);
		list.add(GoBack);

		options.put(AddBrand, list);

		list = new ArrayList<>();
		list.add(AddCustomer);
		list.add(GoBack);

		options.put(AddCustomer, list);

		list = new ArrayList<>();
		list.add(ShowBrandInfo);
		list.add(GoBack);

		options.put(ShowBrandInfo, list);

		list = new ArrayList<>();
		list.add(ShowCustomerInfo);
		list.add(GoBack);

		options.put(ShowCustomerInfo, list);

		list = new ArrayList<>();
		list.add(AddActivityType);
		list.add(GoBack);

		options.put(AddActivityType, list);

		list = new ArrayList<>();
		list.add(AddRewardType);
		list.add(GoBack);

		options.put(AddRewardType, list);
		
		populateBrandOptions();
		populateCustomerOptions();

	}

	public static void populateBrandOptions() {
		List<String> list;

		list = new ArrayList<>();
		list.add(AddLoyaltyProgram);
		list.add(AddRERules);
		list.add(UpdateRERule);
		list.add(AddRRRules);
		list.add(UpdateRRRule);
		list.add(ValidateLoyaltyProgram);
		list.add(Logout);
		options.put(BrandLanding, list);

		list = new ArrayList<>();
		list.add(Regular);
		list.add(Tier);
		list.add(GoBack);
		options.put(AddLoyaltyProgram, list);

		list = new ArrayList<>();
		list.add(ActivityTypes);
		list.add(RewardTypes);
		list.add(GoBack);
		options.put(Regular, list);

		list = new ArrayList<>();
		list.add(Purchase);
		list.add(LeaveReview);
		list.add(ReferFriend);
		list.add(GoBack);
		options.put(ActivityTypes, list);

		list = new ArrayList<>();
		list.add(GiftCard);
		list.add(FreeProduct);
		list.add(GoBack);
		options.put(RewardTypes, list);

		list = new ArrayList<>();
		list.add(TiersSetup);
		list.add(ActivityTypes);
		list.add(RewardTypes);
		list.add(GoBack);
		options.put(Tier, list);

		list = new ArrayList<>();
		list.add(SetUp);
		list.add(GoBack);
		options.put(TiersSetup, list);

		list = new ArrayList<>();
		list.add(AddRERules);
		list.add(GoBack);
		options.put(AddRERules, list);

		list = new ArrayList<>();
		list.add(UpdateRERule);
		list.add(GoBack);
		options.put(UpdateRERule, list);

		list = new ArrayList<>();
		list.add(AddRRRules);
		list.add(GoBack);
		options.put(AddRRRules, list);

		list = new ArrayList<>();
		list.add(UpdateRRRule);
		list.add(GoBack);
		options.put(UpdateRRRule, list);

		list = new ArrayList<>();
		list.add(Validate);
		list.add(GoBack);
		options.put(ValidateLoyaltyProgram, list);
	}


	public static void printOptions(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + 1 + " : " + list.get(i));
		}
		return;
	}

}
