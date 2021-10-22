package util;
import db.DBTasks;


public class SignUp {
	
	public Integer UserId;
	public String userName;
	
	public String passWord;
	public String loginType;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public void takeInput()
	{
		int type;
	
		while(true)
		{
			System.out.println("Enter 1 for brand, any other input will be considered as customer");
			
			type = DisplayOptions.getSc().nextInt();
			
			if(!Input.takeSignupInput(this)) continue;
			
			if(this.getUserName() == null || this.getPassWord() == null)
			{
				System.out.println("Invalid Input !!!!");
				continue;
			}
			
			if(type == 1) this.setLoginType("BRAND");
			
			else this.setLoginType("CUSTOMER");
			
			DisplayOptions.printOptions(DisplayOptions.options.get("Sign-up"));
			
			int option = DisplayOptions.getSc().nextInt();
			
			if(option >= 3)
			{
				System.out.println("Invalid input !!!"); continue;
			}
			
			if(option == 2) 
			{
				return;
			}
			
			try
			{
				String query = "insert into users(user_id,user_name, password, login_type) values (user_id_sequence.nextval,?,?,?)";
					
				DBTasks.insertLoginData(query, this);
				
				System.out.println("Registered successfully !!!!1");
				
			//	DBTasks.getConn().commit();
				
				new Login().takeInput();
				
				return;
			
			}
			catch(Exception e)
			{
				System.out.println("Username aldready exists !!!"); continue;
			}
		}
		
	}

}
