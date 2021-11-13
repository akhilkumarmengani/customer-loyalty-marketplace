package models;

import db.DBTasks;
import util.AppData;
import util.DisplayOptions;
import util.Input;

public class AdminLanding {

	public static void addBrand()
	{
		Brand brandObj = null;

		while(true)
		{
			brandObj = new Brand();

			if(!Input.takeBrandSignupInput(brandObj))
			{
				continue;
			}

			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.AddBrand));

			int option = DisplayOptions.getSc().nextInt();

			if(option < 1 || option > 2)
			{
				System.out.println("Invalid Option"); continue;
			}
			if(option == 2) return;

			try
			{
				String query = "insert into users(user_name, passwd, login_type, name, address, contact_number) "
						+ "values (?,?,?,?,?,?)";

				DBTasks.insertBrandLoginData(query, brandObj);

				System.out.println("Added brand successfully !!!");

				return;

			}
			catch(Exception e)
			{
				continue;
			}

		}
	}

	public static void addCustomer()
	{
		Customer custObj = null;

		while(true)
		{
			custObj = new Customer();

			if(!Input.takeCustomerSignupInput(custObj))
			{
				continue;
			}

			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.AddCustomer));

			int option = DisplayOptions.getSc().nextInt();

			if(option < 1 || option > 2)
			{
				System.out.println("Invalid Option"); continue;
			}
			if(option == 2) return;

			try
			{
				String query = "insert into users(user_name, passwd, login_type, name, address, contact_number) "
						+ "values (?,?,?,?,?,?)";

				DBTasks.insertCustomerLoginData(query, custObj);

				System.out.println("Added customer successfully !!!");

				return;
			}
			catch(Exception e)
			{
				continue;
			}
		}
	}

	public static void showBrandInfo()
	{

		Brand brandObj = null;

		while(true)
		{

			System.out.println("Enter brand user name. ");

			String brandUserName = DisplayOptions.getSc().next();

			if(null == brandUserName || "".equals(brandUserName))
			{
				System.out.println("Invalid user name !!");
				continue;
			}

			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.ShowBrandInfo));

			int option = DisplayOptions.getSc().nextInt();

			if(option < 1 || option > 2)
			{
				System.out.println("Invalid Option"); continue;
			}
			if(option == 2) return;

			try
			{

				brandObj = DBTasks.getBrandInfo(brandUserName);

				if(brandObj != null)
				{
					System.out.println("Brand Name: "+brandObj.getBrandName());

					System.out.println("Brand Address: "+brandObj.getAddress());

					//System.out.println("Brand Contact Number :"+brandObj.getContactNumber()+"\n");
				}

			}
			catch(Exception e)
			{

			}

		}
	}

	public static void showCustomerInfo()
	{
		Customer custObj = null;

		while(true)
		{

			System.out.println("Enter customer user name. ");

			String customerUserName = DisplayOptions.getSc().next();

			if(null == customerUserName || "".equals(customerUserName))
			{
				System.out.println("Invalid user name !!");

				continue;
			}

			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.ShowCustomerInfo));

			int option = DisplayOptions.getSc().nextInt();

			if(option < 1 || option > 2)
			{
				System.out.println("Invalid Option"); continue;
			}
			if(option == 2) return;

			try
			{

				custObj = DBTasks.getCustomerInfo(customerUserName);

				if(custObj != null)
				{
					System.out.println("Customer Name: "+custObj.getCustomerName());

					System.out.println("Customer Address: "+custObj.getAddress());

					System.out.println("Customer Contact Number :"+custObj.getContactNumber()+"\n");
				}

			}
			catch(Exception e)
			{

			}
		}
	}

	public static void addActivityType()
	{
		Activity actObj = null;

		while(true)
		{
			actObj = new Activity();

			if(!Input.takeActivityInput(actObj)) continue;

			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.AddActivityType));

			int option = DisplayOptions.getSc().nextInt();

			if(option < 1 || option > 2)
			{
				System.out.println("Invalid Option"); continue;
			}
			if(option == 2) return;

			try
			{
				String query = "insert into activities(activity_name, activity_code) "
						+ "values (?,?)";

				DBTasks.insertActivityData(query, actObj);

				System.out.println("Added activity successfully !!!");


			}
			catch(Exception e)
			{
				continue;
			}
		}
	}

	public static void addRewardType()
	{
		Reward rewObj = null;

		while(true)
		{
			rewObj = new Reward();

			if(!Input.takeRewardInput(rewObj)) continue;

			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.AddRewardType));

			int option = DisplayOptions.getSc().nextInt();

			if(option < 1 || option > 2)
			{
				System.out.println("Invalid Option"); continue;
			}
			if(option == 2) return;

			try
			{
				String query = "insert into reward_categories(reward_name, reward_category_code) "
						+ "values (?,?)";

				DBTasks.insertRewardData(query, rewObj);

				System.out.println("Added reward successfully !!!");


			}
			catch(Exception e)
			{
				continue;
			}
		}

	}

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
					addBrand();
					break;
				}
				case 2:
				{
					addCustomer();
					break;
				}
				case 3:
				{
					showBrandInfo();
					break;
				}
				case 4:
				{
					showCustomerInfo();
					break;
				}
				case 5:
				{
					addActivityType();
					break;
				}
				case 6:
				{
					addRewardType();
					break;
				}
				case 7:
				{
					AppData.brandLogout = true;
					return;
				}

			}
		}
	}
}
