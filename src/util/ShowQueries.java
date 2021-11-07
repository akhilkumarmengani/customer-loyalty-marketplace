package util;

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
				System.out.println(showQuery);
				
				System.out.println("select an option !!!");
				
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
						ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery1());
						break;
					}
					case 2:
					{
						ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery2());
						break;
					}
					case 3:
					{
						ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery3());
						break;
					}
					case 4:
					{
						ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery4());
						break;
					}
					case 5:
					{
						ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery5());
						break;
					}
					case 6:
					{
						ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery6());
						break;
					}
					case 7:
					{
						ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery7());
						break;
					}
					case 8:
					{
						ShowQueriesUtil.printQueryResult(ShowQueriesUtil.executeQuery8());
						break;
					}
				}
				
			}
		}
}
