package util;
import java.sql.ResultSet;

import db.DBTasks;

public class Login {
	
		public String UserId;
		public String passWord;
		
		public void takeInput()
		{
			int option;
			
			while(true)
			{
				Input.takeLoginInput(this);
				
				if(this.getUserId() == null || this.getPassWord() == null)
				{
					System.out.println("Invalid Input");
					continue;
				}
				
				DisplayOptions.printOptions(DisplayOptions.options.get("Login"));
				
				option = DisplayOptions.getSc().nextInt();
				
				if(option == 2) return;
				
				try
				{
					String Query = "select USER_NAME, USER_PASSWORD, LOGIN_TYPE from LOGIN "
							+ "where USER_NAME = ? and USER_PASSWORD = ?";
					
					ResultSet rs = DBTasks.executeQueryForLogin(Query, this);
					
					if(rs == null || !rs.next())
					{
						System.out.println("Invalid Login !!!!"); continue;
					}
					
					System.out.println(rs.getString("USER_NAME")+" "+rs.getString("USER_PASSWORD")+" "
							+rs.getString("LOGIN_TYPE"));
					
				}
				catch(Exception e)
				{
					
				}
				
			}
			
		}

		public String getUserId() {
			return UserId;
		}

		public void setUserId(String userId) {
			UserId = userId;
		}

		public String getPassWord() {
			return passWord;
		}

		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}
}
