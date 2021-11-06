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
	public static final String Logout = "Logout";


	public static void populateOptions()
	{
		List<String> list;
		
		list = new ArrayList<>();
		list.add(Login); list.add(SignUp); list.add(ShowQueries); list.add(Exit);
		
		options.put(Home,list);
		
		list = new ArrayList<>();
		list.add(SignIn); list.add(GoBack); 
		
		options.put(Login,list);
		
		list = new ArrayList<>();
		list.add(BrandSignUp); list.add(CustomerSignUp); list.add(GoBack);
		
		options.put(SignUp,list);
		
		list = new ArrayList<>();
		list.add(SignUp); list.add(GoBack);
		
		options.put(BrandSignUp,list);
		
		list = new ArrayList<>();
		list.add(SignUp); list.add(GoBack);
		
		options.put(CustomerSignUp,list);
		
		list = new ArrayList<>();
		list.add(AddBrand); list.add(AddCustomer); list.add(ShowBrandInfo); 
		list.add(ShowCustomerInfo); list.add(AddActivityType); list.add(AddRewardType); 
		list.add(Logout);
		
		options.put(AdminLanding,list);
		
		list = new ArrayList<>();
		list.add(AddBrand); list.add(GoBack); 
		
		options.put(AddBrand,list);
		
		list = new ArrayList<>();
		list.add(AddCustomer); list.add(GoBack); 
		
		options.put(AddCustomer,list);
		
		list = new ArrayList<>();
		list.add(ShowBrandInfo); list.add(GoBack); 
		
		options.put(ShowBrandInfo,list);
		
		list = new ArrayList<>();
		list.add(ShowCustomerInfo); list.add(GoBack);
		
		options.put(ShowCustomerInfo,list);
		
		list = new ArrayList<>();
		list.add(AddActivityType); list.add(GoBack); 
		
		options.put(AddActivityType,list);
		
		list = new ArrayList<>();
		list.add(AddRewardType); list.add(GoBack); 
		
		options.put(AddRewardType,list);
		
		
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
