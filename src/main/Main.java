package main;
import java.sql.*;
import java.util.*;

import db.DBTasks;
import util.DisplayOptions;
import util.Login;
import util.ShowQueries;
import util.SignUp;

public class Main {
	
	// This is the file which needs to be run to start the application. 
	
	public static Map<String,List<String>> options;
		
	public static void main(String[] args) throws ClassNotFoundException
	{
		
			DisplayOptions.populateOptions();  // 	Populate all the control flows in this method.
			
			options = DisplayOptions.options;
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			try
			{
				Scanner sc = DisplayOptions.getSc();
				
				DBTasks.setConn(DriverManager.getConnection(
						DBTasks.getJdbcurl(), DBTasks.getUsername(), DBTasks.getPassword()));  // Set the DB connection.
				

				int option;
			
				List<String> inputList = options.get(DisplayOptions.Home);
				
				while(true)
				{
					DisplayOptions.printOptions(inputList);
					
					System.out.println("Enter the choice");
					
					option = sc.nextInt();
					
					if(option < 1 || option > 4)
					{
						System.out.println("Invalid Input");
						
						continue;
					}
					
					if(option == 1) new Login().takeInput();
						
					if(option == 2) new SignUp().takeInput();
						
					if(option == 3) new ShowQueries().DisplayQueriesAndResults();
		
					if(option == 4) break;
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
			finally
			{
				Connection conn = DBTasks.getConn();
				if (conn != null) {
						DBTasks.resetConn();
				}
				System.out.println("****** You are Logged Out of Customer Loyalty Marketplace ******");
				System.out.println();
			}
			
			return;
	}
}
