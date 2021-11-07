package util;
import java.sql.ResultSet;

import brands.BrandsUtil;
import customers.CustomerLanding;
import customers.CustomerLandingUtil;
import brands.BrandLanding;
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
					String Query = "select * from users "
							+ "where USER_NAME = ? and PASSWD = ?";
					
					ResultSet rs = DBTasks.executeQueryForLogin(Query, this);
					
					if(rs == null || !rs.next())
					{
						System.out.println("Invalid Login !!!!"); continue;
					}
					
					System.out.println("Login Successful !!!!");
					
					String login_type = rs.getString("LOGIN_TYPE");
//					System.out.println(login_type);
					
					int user_id = Integer.valueOf(rs.getInt("USER_ID"));
//					System.out.println(user_id);

					switch(login_type)
					{
						case "ADMIN":
						{
							new AdminLanding().takeInput();
						}
						case "CUSTOMER":{
							int customerId = 
									CustomerLandingUtil.getCustomerIdByUserId(Integer.valueOf(user_id));
//							System.out.println("Login Customer : "+customerId);
							AppData.customerId = Integer.valueOf(customerId);
							new CustomerLanding().takeInput();
						}
						case "BRAND": {
							BrandsUtil.initializeAllFields(this);
							new BrandLanding().takeInput();
							return;
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
