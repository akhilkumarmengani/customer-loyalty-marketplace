package util;
import java.sql.ResultSet;
import customers.CustomerLanding;

import db.DBTasks;
import models.AdminLanding;

public class Login {
	
		public String userName;
		public String passWord;
		
		public void takeInput()
		{
			int option;
			
			while(true)
			{
				if(!Input.takeLoginInput(this)) continue;
				
				break;
			}
			
			while(true)
			{
			
				DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.Login));
				
				option = DisplayOptions.getSc().nextInt();
				
				if(option < 1 || option > 2)
				{
					System.out.println("Invalid Option"); continue;
				}
				
				if(option == 2) return;
				
				try
				{
					String Query = "select USER_NAME, PASSWD, LOGIN_TYPE from users "
							+ "where USER_NAME = ? and PASSWD = ?";
					
					ResultSet rs = DBTasks.executeQueryForLogin(Query, this);
					
					if(rs == null || !rs.next())
					{
						System.out.println("Invalid Login !!!!"); continue;
					}
					
					System.out.println("Login Successful !!!!");
					
					String login_type = rs.getString("LOGIN_TYPE");
					
					switch(login_type)
					{
						case "ADMIN":
						{
							new AdminLanding().takeInput();
						}
						case "CUSTOMER":{
							new CustomerLanding().takeInput();
						}
					}
					
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
