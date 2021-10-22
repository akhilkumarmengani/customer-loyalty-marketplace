package util;
import java.sql.ResultSet;

import db.DBTasks;

public class Login {
	
		public String userName;
		public String passWord;
		
		public void takeInput()
		{
			int option;
			
			while(true)
			{
				if(!Input.takeLoginInput(this)) continue;
				
				if(this.getUserName() == null || this.getPassWord() == null)
				{
					System.out.println("Invalid Input");
					continue;
				}
				
				DisplayOptions.printOptions(DisplayOptions.options.get("Login"));
				
				option = DisplayOptions.getSc().nextInt();
				
				if(option == 2) return;
				
				try
				{
					String Query = "select USER_NAME, PASSWORD, LOGIN_TYPE from users "
							+ "where USER_NAME = ? and PASSWORD = ?";
					
					ResultSet rs = DBTasks.executeQueryForLogin(Query, this);
					
					if(rs == null || !rs.next())
					{
						System.out.println("Invalid Login !!!!"); continue;
					}
					
					System.out.println(rs.getString("USER_NAME")+" "+rs.getString("PASSWORD")+" "
							+rs.getString("LOGIN_TYPE"));
					
				}
				catch(Exception e)
				{
					
				}
				
			}
			
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassWord() {
			return passWord;
		}

		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}
}
