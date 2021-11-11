package util;

import java.util.Locale;

public class ShowQueries {

	public static String showQuery = "1. List all customers that are not part of Brand02’s program.\n"
			+ "2. List customers that have joined a loyalty program but have not participated in any activity\n"
			+ "in that program (list the customerid and the loyalty program id).\n"
			+ "3. List the rewards that are part of Brand01 loyalty program.\n"
			+ "4. List all the loyalty programs that include “refer a friend” as an activity in at least one of\n"
			+ "their reward rules.\n"
			+ "5. For Brand01, list for each activity type in their loyalty program, the number instances that\n"
			+ "have occurred.\n"
			+ "6. List customers of Brand01 that have redeemed at least twice.\n"
			+ "7. All brands where total number of points redeemed overall is less than 500 points\n"
			+ "8. For Customer C0003, and Brand02, number of activities they have done in the period of\n"
			+ "08/1/2021 and 9/30/2021.";

	public void DisplayQueriesAndResults()
	{
		while(true)
		{
			System.out.println();
			System.out.println("-------------Select Queries-----------------");
			System.out.println();
			System.out.println(showQuery);
			System.out.println();
			System.out.println("Enter a number from the above query list:");

			int option = DisplayOptions.getSc().nextInt();

			if(option < 1 || option > 8)
			{
				System.out.println("Invalid option");
				continue;
			}

			switch(option)
			{
				case 1:
				{
					String brandName;
					System.out.println("\nEnter Brand Name:");
					brandName = DisplayOptions.getSc().next();
					System.out.println();
					ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery1(brandName));
					break;
				}
				case 2:
				{
					ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery2());
					break;
				}
				case 3:
				{
					String customerName;
					System.out.println("\nEnter Customer Name: ");
					customerName = DisplayOptions.getSc().next();
					System.out.println();
					ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery3(customerName));
					break;
				}
				case 4:
				{
					System.out.println("\nList of activities and their codes: ");
					System.out.println("Purchase    :   ACT101");
					System.out.println("Refer a friend    :   ACT102");
					System.out.println("Write a review    :   ACT103");
					System.out.println("Enter activity code:");
					String activityCode = DisplayOptions.getSc().next();
					System.out.println();
					ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery4(activityCode));
					break;
				}
				case 5:
				{
					String brandName;
					System.out.println("\nEnter Brand Name:");
					brandName = DisplayOptions.getSc().next();
					System.out.println();
					ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery5(brandName));
					break;
				}
				case 6:
				{
					String brandName;
					System.out.println("\nEnter Brand Name:");
					brandName = DisplayOptions.getSc().next();
					System.out.println();
					ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery6(brandName));
					break;
				}
				case 7:
				{
					int pointsThreshold;
					System.out.println("\nEnter the points redeeming threshold:");
					pointsThreshold = DisplayOptions.getSc().nextInt();
					System.out.println();
					ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery7(pointsThreshold));
					break;
				}
				case 8:
				{
					String brandName, customerName, startDate, endDate;
					System.out.println("\nEnter Brand Name:");
					brandName = DisplayOptions.getSc().next();
					System.out.println();
					System.out.println("\nEnter Customer Name: ");
					customerName = DisplayOptions.getSc().next();
					System.out.println();
					System.out.println("\nEnter Start Date (MM/DD/YYYY): ");
					startDate = DisplayOptions.getSc().next();
					System.out.println();
					System.out.println("\nEnter End Date (MM/DD/YYYY): ");
					endDate = DisplayOptions.getSc().next();
					System.out.println();
					ShowQueriesUtil.executeQuery8(brandName,customerName,startDate,endDate);
					break;
				}
			}
			System.out.println("\n"+"Enter no to exit and any other to continue");
			String op = DisplayOptions.getSc().next();
			if(op != null && "no".equals(op.toLowerCase())) break;
			//System.out.println("------------------------------------------------");
		}
	}
}
