package util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DisplayOptions {
	
	public static Map<String,List<String>> options = new HashMap<>();
	
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

	public static void populateOptions()
	{
		List<String> list;
		
		list = new ArrayList<>();
		list.add("Login"); list.add("Sign Up"); list.add("Show Queries"); list.add("Exit");
		
		options.put("Home",list);
		
		list = new ArrayList<>();
		list.add("Sign-in"); list.add("Go Back"); 
		
		options.put("Login",list);
		
		list = new ArrayList<>();
		list.add("Sign-up"); list.add("Go Back");
		
		options.put("Sign-up",list);
		
		list = new ArrayList<>();
		list.add("Add brand"); list.add("Add customer"); list.add("Show brand's info"); 
		list.add("Show customer's info"); list.add("Add activity type"); list.add("Add reward type"); 
		list.add("Logout");
		
		options.put("Login-Admin",list);
		
		list = new ArrayList<>();
		list.add("addBrand"); list.add("Go Back"); 
		
		options.put("Add brand",list);
		
		list = new ArrayList<>();
		list.add("addCustomer"); list.add("Go Back"); 
		
		options.put("Add customer",list);
		
		list = new ArrayList<>();
		list.add("showBrandInfo"); list.add("Go Back"); 
		
		options.put("Show brand's info",list);
		
		list = new ArrayList<>();
		list.add("showCustomerInfo"); list.add("Go Back");
		
		options.put("Show customer's info",list);
		
		list = new ArrayList<>();
		list.add("addActivityType"); list.add("Go Back"); 
		
		options.put("Add activity type",list);
		
		list = new ArrayList<>();
		list.add("addRewardType"); list.add("Go Back"); 
		
		options.put("Add reward type",list);
		
		list = new ArrayList<>();
		list.add("addLoyaltyProgram"); list.add("addRERules"); list.add("updateRERules");
		list.add("addRRRules"); list.add("updateRRRules"); list.add("validateLoyaltyProgram");
		list.add("Logout");
		
		options.put("Login-Brand",list);
		
	}
	
	public static void printOptions(List<String> list)
	{
		for(int i=0; i< list.size(); i++)
		{
			System.out.println(i+1+" : "+list.get(i));
		}
		return;
	}
	
}
