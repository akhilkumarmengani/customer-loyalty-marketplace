package db;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import models.Brand;
import models.Customer;
import models.RegularLoyaltyProgram;
import models.TierLoyaltyProgram;
import util.AppData;
import util.Login;

public class DBTasks {
	
	public static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
	
	public static final String userName = "smulkur";
	
	public static final String passWord = "abcd1234";
	
	public static Connection conn;
	
    public static PreparedStatement stmt;
    
    public static ResultSet rs;

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		DBTasks.conn = conn;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		DBTasks.rs = rs;
	}

	public static String getJdbcurl() {
		return jdbcURL;
	}

	public static String getUsername() {
		return userName;
	}

	public static String getPassword() {
		return passWord;
	}
	
	public static ResultSet executeQueryForLogin(String query, Login login)
	{
		try
		{
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, login.getUserName());
			
			stmt.setString(2, login.getPassWord());
		
			rs = stmt.executeQuery();
			
			return rs;
			
		}
		catch(Exception e)
		{
			return  null;
		}
	}
	
	public static void insertBrandLoginData(String query, Brand brandObj) throws Exception
	{
		try
		{
			stmt = conn.prepareStatement(query);
			

			stmt.setString(1, brandObj.getUserName());
			
			stmt.setString(2, brandObj.getPassWord());
			
			stmt.setString(3, "BRAND");
			
			stmt.setString(4, brandObj.getBrandName());
			
			stmt.setString(5, brandObj.getAddress());
			
			stmt.setInt(6, brandObj.getContactNumber());

			stmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			
			throw new Exception(e);
		}
		
	}
	
	public static void insertCustomerLoginData(String query, Customer custObj) throws Exception
	{
		try
		{
			stmt = conn.prepareStatement(query);
			

			stmt.setString(1, custObj.getUserName());
			
			stmt.setString(2, custObj.getPassWord());
			
			stmt.setString(3, "CUSTOMER");
			
			stmt.setString(4, custObj.getCustomerName());
			
			stmt.setString(5, custObj.getAddress());
			
			stmt.setInt(6, custObj.getContactNumber());

			stmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			
			throw new Exception(e);
		}
		
	}
<<<<<<< HEAD

=======
<<<<<<< HEAD

	public static Brand getBrandInfo(String userName)
	{
		try
		{
			String query = "select name, address, contact_number from users where user_name = ?";
			
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, userName);
			
			rs = stmt.executeQuery();
			
			if(rs == null || !rs.next())
			{
				System.out.println("Corresponding brand is not present !!!");
				
				return null;
			}
			
			Brand brandObj = new Brand();
			
			brandObj.setBrandName(rs.getString("name"));
			
			brandObj.setAddress(rs.getString("address"));
			
			brandObj.setContactNumber(rs.getInt("contact_number"));
			
			return brandObj;
=======
	
	public static void insertRegularLoyaltyData(String query, RegularLoyaltyProgram regularLPObj) throws Exception
	{
		try
		{
			
			
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, regularLPObj.getBrandId());
			
			stmt.executeUpdate();
			
			
>>>>>>> df031a0a9c777117d072d057b184f6e421d09db4
			
		}
		catch(Exception e)
		{
<<<<<<< HEAD
			return null;
		}
	}
	
	public static Customer getCustomerInfo(String userName)
	{
		try
		{
			String query = "select name, address, contact_number from users where user_name = ?";
			
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, userName);
			
			rs = stmt.executeQuery();
			
			if(rs == null || !rs.next())
			{
				System.out.println("Corresponding customer is not present !!!");
				
				return null;
			}
			
			Customer custObj = new Customer();
			
			custObj.setCustomerName(rs.getString("name"));
			
			custObj.setAddress(rs.getString("address"));
			
			custObj.setContactNumber(rs.getInt("contact_number"));
			
			return custObj;
=======
			System.out.println(e.toString());
			
			throw new Exception(e);
		}
	}
>>>>>>> c109390a77226e8e443ba62bf3b6f2144e9dfd65
	
	public static void insertIntoCustomersToBrands(int customerId, int brandId) throws Exception {
		System.out.println(customerId+" "+brandId);

		String query = "insert into customers_to_brands(customer_id,brand_id)  values (?,?)";
		try
		{
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, String.valueOf(customerId));
			
			stmt.setString(2,  String.valueOf(brandId));
			
			stmt.executeUpdate();
>>>>>>> df031a0a9c777117d072d057b184f6e421d09db4
			
		}
		catch(Exception e)
		{
<<<<<<< HEAD
			return null;
		}
	}


=======
			System.out.println(e.toString());
			
			throw new Exception(e);
		}
	}			
	
	
	public static ResultSet getAllBrandLoyaltyPrograms() throws Exception{
		String query = "select * from brands";
		
		rs = stmt.executeQuery(query);

		return rs;
	
	
<<<<<<< HEAD
	}
	
	public static Integer getCustomerIdByUserId(int userId) throws Exception {
		
		Integer customerId = null;
		
		String query = "select *  from customers where user_id = ?";
		
		stmt = conn.prepareStatement(query);
		
		stmt.setString(1, String.valueOf(userId));
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			customerId = rs.getInt("CUSTOMER_ID");
		}
		
		return customerId;
	}
	
	public static boolean isEnrolledInLoyaltyProgram(int customerId, int brandId) throws SQLException {
		System.out.println(customerId+ " "+ brandId);

		
		String query = "select * from customers_to_brands where customer_id = ? and brand_id = ?";
		
		stmt = conn.prepareStatement(query);
		
		stmt.setString(1, String.valueOf(customerId));
		
		stmt.setString(2, String.valueOf(brandId));
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			return true;
		}
		
		return false;
	}
	
	
	public static List<String[]> getBrandLoyaltyPrograms(int customerId) throws SQLException {
		
		String query = "select a.brand_id,b.name from customers_to_brands a,brands b " +
				"where a.customer_id = ? and a.brand_id = b.brand_id";

		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, customerId);

			List<String[]> brandIdList = new ArrayList<>();

			ResultSet rs = stmt.executeQuery();

			if (rs == null || !rs.next()) {
				return null;
			}
			do {
				String idName[] = new String[2];
				idName[0] = String.valueOf(rs.getInt(1));
				idName[1] = rs.getString(2);
				brandIdList.add(idName);

			} while (rs.next());
			return brandIdList;
		}
		catch (Exception ex){
			System.out.println(ex.toString()+" "+ex.getMessage());
		}
	
		return null;
	}
	
	public static List<String[]> getActivitiesForBrand(int brandId) throws SQLException{

		String query = "select a.activity_code,a.activity_name from activities a,brands_loyalty_programs_to_activities b " +
				"where b.brand_id = ? and a.activity_code = b.activity_code";
				
		try {

			stmt = conn.prepareStatement(query);

			stmt.setInt(1, brandId);

			ResultSet rs = stmt.executeQuery();

			List<String[]> list = new ArrayList<>();

			if (rs == null || !rs.next()) {
				return null;
			}

			do {
				String[] idName = new String[2];
				idName[0] = rs.getString(1);
				idName[1] = rs.getString(2);
				list.add(idName);
			} while (rs.next());

			return list;
		}
		catch(Exception ex){
			System.out.println(ex.toString()+" "+ex.getMessage());
		}
		return null;
	}

	public static List<String[]> getAllGiftCardsOfCustomer(int customerId, int brandId){

		try{

			String query = "select b.reward_value,a.number_of_instances from customers_to_blp_rewards a, brands_loyalty_programs_to_rewards b " +
					"where a.customer_id=? and a.brand_id=? and a.u_id = b.u_id and a.reward_category_code = ? ";

			stmt = conn.prepareStatement(query);

			stmt.setInt(1, customerId);
			stmt.setInt(2, brandId);
			stmt.setString(3, "REW101");

			ResultSet rs = stmt.executeQuery();

			List<String[]> list =  new ArrayList<>();

			if (rs == null || !rs.next()) {
				return null;
			}

			do {
				String[] idName = new String[4];
				idName[0] = rs.getString(1);
				idName[1] = rs.getString(2);
				list.add(idName);
			} while (rs.next());


			return list;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()+" "+ex.toString());
		}

		return null;
	}

	public static int insertIntoCustomerToBLPActivities(int customerId, int brandId, String activityCode,
														int giftCardValue, int numberOfInstances
														) throws SQLException {
		try{
		String query = "select  c.customer_id as customer_id,\n" +
				"        a.brand_id as brand_id,\n" +
				"        a.loyalty_program_id as loyalty_program_id,\n" +
				"        c.wallet_id as wallet_id, \n" +
				"        a.activity_code as activity_code,\n" +
				"		 a.activity_value * nvl(b.multiplier,1) as POINTS_EARNED, \n" +
				"        a.u_id as u_id\n" +
				"from    brands_loyalty_programs_to_activities a, \n" +
				"        tiered_loyalty_programs b, \n" +
				"        customer_wallet_to_customers_brands c\n" +
				"where   a.brand_id = ?\n" +
				"        and a.activity_code = ?\n" +
				"        and c.brand_id = a.brand_id\n" +
				"        and c.customer_id = ?\n" +
				"        and c.brand_id = b.brand_id (+) \n" +
				"        and c.loyalty_program_id = b.loyalty_program_id (+) \n" +
				"        and c.loyalty_program_level = b.loyalty_program_level (+) \n" +
				"order by version_number desc \n" +
				"fetch next 1 rows only";

		stmt = conn.prepareStatement(query);

		stmt.setInt(1, brandId);
		stmt.setString(2, activityCode);
		stmt.setInt(3,customerId);

		ResultSet rs = stmt.executeQuery();

		if (rs == null || !rs.next()) {
			return -1;
		}

		int cId = rs.getInt(1);
		int bId = rs.getInt(2);
		int lpId = rs.getInt(3);
		int wId = rs.getInt(4);
		String actCode = rs.getString(5);
		int ptsEarned = rs.getInt(6);
		int uId = rs.getInt(7);

		if(giftCardValue>0){
			String updateQuery = "update customers_to_blp_rewards " +
					"set number_of_instances = ? where customer_id = ? and brand_id = ? and reward_category_code = ?";

			stmt = conn.prepareStatement(updateQuery);

			stmt.setInt(1, numberOfInstances-1);
			stmt.setInt(2,customerId);
			stmt.setInt(3,brandId);
			stmt.setString(4,"REW101");

			stmt.executeUpdate();

		}


		long millis = System.currentTimeMillis();
		Date date = new java.sql.Date(millis);

//
//		SimpleDateFormat sdf = new SimpleDateFormat(
//				"MM-dd-yyyy");
//		int year = 2014;
//		int month = 10;
//		int day = 31;
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, year);
//		cal.set(Calendar.MONTH, month - 1); // <-- months start
//		// at 0.
//		cal.set(Calendar.DAY_OF_MONTH, day);
//
//		java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
//		System.out.println(sdf.format(date));



		String selectQuery = "select points_earned from customers_to_blp_activities where customer_id=? and brand_id=? and " +
				" activity_code=? and performed_date = to_char( ? , 'DD-MON-yy') ";

		stmt = conn.prepareStatement(selectQuery);

		stmt.setInt(1, customerId);
		stmt.setInt(2, brandId);
		stmt.setString(3, "ACT101");
		stmt.setDate(4,java.sql.Date.valueOf(java.time.LocalDate.now()));


		rs = stmt.executeQuery();


		if(rs!= null && rs.next())
		{
			String updateQuery = "update customers_to_blp_activities(points_earned = ?) where brand_id = ? and customer_id = ? "+
					"and activity_code = ? and performed_date = ?";

			stmt = conn.prepareStatement(updateQuery);

			stmt.setInt(1,rs.getInt("points_earned") + ptsEarned - giftCardValue);
			stmt.setInt(2,customerId);
			stmt.setInt(3,brandId);
			stmt.setString(4,"ACT101");
			stmt.setDate(5,date);

			stmt.executeUpdate();


		}
		else {
			String insertQuery = "insert into customers_to_blp_activities(customer_id,brand_id,loyalty_program_id,wallet_id,activity_code,points_earned,performed_date,u_id) \n" +
					"values(?,?,?,?,?,?,?,?)";


			stmt = conn.prepareStatement(insertQuery);

			stmt.setInt(1, cId);
			stmt.setInt(2, bId);
			stmt.setInt(3, lpId);
			stmt.setInt(4, wId);
			stmt.setString(5, actCode);
			stmt.setInt(6, ptsEarned-giftCardValue);
			stmt.setDate(7,date);
			stmt.setInt(8,uId);

			stmt.executeUpdate();
		}



		return ptsEarned;

		}
		catch (Exception ex){
			System.out.println(ex.getMessage()+" "+ex.toString());
		}

		return -1;
	}
	
	
	public static List<String[]> getAllCurrentRewardsForABrand(int brandId)
	{
		try {


			String query = "select a.reward_value, a.number_of_instances, a.reward_category_code, b.reward_name, a.u_id\n" +
					"from brands_loyalty_programs_to_rewards a, reward_categories b \n" +
					"where a.brand_id = ?\n" +
					"and a.reward_category_code = b.reward_category_code\n" +
					"and a.version_number = ( select max(version_number) \n" +
					"                            from brands_loyalty_programs_to_rewards c\n" +
					"                            where c.brand_id = a.brand_id\n" +
					"                            and c.reward_category_code = a.reward_category_code\n" +
					"                        )";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,brandId);
			rs = stmt.executeQuery();

			if(rs == null || !rs.next())
			{
				// System.out.println("There are no rewards present for the selected brand, please select another brand. ");
				return null;
			}

			List<String[]> list = new ArrayList<String[]>();

			do {
				String[] str = new String[5];

				str[0] = rs.getString(1);
				str[1] = String.valueOf(rs.getInt(2));
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				str[4] = String.valueOf(rs.getInt(5));

				list.add(str);
			}while(rs.next());

			return list;
		}
		catch(Exception e)
		{
			System.out.println(e.toString()+" "+e.getMessage());
			return null;
		}
	}

	public static int getTotalWalletPointsForACustomerInBrand(int customerId, int brandId) {
		try {


			String query = "select total_points from customer_wallet_to_customers_brands where brand_id = ? and customer_id = ?";

			stmt = conn.prepareStatement(query);

			stmt.setInt(1, brandId);
			stmt.setInt(2, customerId);

			rs = stmt.executeQuery();

			if (rs == null ||
					!rs.next()) return 0;

			return rs.getInt(1);

		}
		catch (Exception e) {
			System.out.println(e.toString() + " " + e.getMessage());

		}
		return 0;
	}

	public static int getLoyaltyId(int brandId)
	{
		try
		{
			String query = "select loyalty_program_id from regular_loyalty_programs where brand_id = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,brandId);
			rs = stmt.executeQuery();

			if(rs == null || !rs.next()) return 0;
			return rs.getInt(1);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public static void updateCustomerRewardsForABrand(int customerId, int brandId, int[] totalRewards, int uid[])
	{
		try
		{
			String[] rewCodes = new String[]{"REW101","REW102"};

			int loyaltyId = getLoyaltyId(brandId);

			for(int i=0; i<2; i++)
			{
				if(totalRewards[i] > 0) {
					String query = "select number_of_instances from customers_to_blp_rewards " +
							"where customer_id = ? and brand_id = ? and reward_category_code = ?";

					stmt = conn.prepareStatement(query);

					stmt.setInt(1, customerId);
					stmt.setInt(2, brandId);
					stmt.setString(3, rewCodes[i]);

					rs = stmt.executeQuery();

					if (rs == null || !rs.next()) {
						String insertQuery = "insert into customers_to_blp_rewards(customer_id, brand_id, loyalty_program_id," +
								" reward_category_code, number_of_instances, u_id) values(?,?,?,?,?,?)";

						stmt = conn.prepareStatement(insertQuery);
						stmt.setInt(1, customerId);
						stmt.setInt(2, brandId);
						stmt.setInt(3, loyaltyId);
						stmt.setString(4, rewCodes[i]);
						stmt.setInt(5, totalRewards[i]);
						stmt.setInt(6,uid[i]);

						stmt.executeUpdate();
					}
					else
					{
						String updateQuery = "update customers_to_blp_rewards set number_of_instances = ? " +
								"where brand_id = ? and customer_id = ? and reward_category_code = ?";


						stmt = conn.prepareStatement(updateQuery);

						stmt.setInt(1,rs.getInt(1)+ totalRewards[i]);
						stmt.setInt(2,brandId);
						stmt.setInt(3,customerId);
						stmt.setString(4,rewCodes[i]);


						stmt.executeUpdate();
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.toString()+" "+e.getMessage());
		}
	}

	public static void displayWallet(int customerId){
		String query = "select c.name, a.activity_name,  to_char(b.performed_date, 'DD-MON-YY') , sum(b.points_earned) as points_earned\n" +
				"from ACTIVITIES a, CUSTOMERS_TO_BLP_ACTIVITIES b, BRANDS c\n" +
				"where b.CUSTOMER_ID = ?\n" +
				"and  b.activity_code = a.activity_code\n" +
				"and b.brand_id = c.brand_id\n" +
				"group by c.name, a.activity_name, to_char(b.performed_date, 'DD-MON-YY')";
		try{
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();

			if(rs==null || !rs.next()){
				System.out.println("Empty Wallet!");
				return ;
			}

			//System.out.println("------Customer Wallet-------");

			do{
				System.out.println("Brand Name : "+ rs.getString(1)+" | Activity Name : "+ rs.getString(2)+"" +
						" | Performed Date : "+ rs.getString(3)+" | Points Earned :"+rs.getInt(4));
			}while(rs.next());

		}
		catch (Exception ex){
			System.out.println(ex.toString()+" "+ ex.getMessage());
		}

	}

	public static void insertIntoCustomerToBLPActivitiesForReview(int customerId, int brandId, String activityCode){
		try{
			String query = "select  c.customer_id as customer_id,\n" +
					"        a.brand_id as brand_id,\n" +
					"        a.loyalty_program_id as loyalty_program_id,\n" +
					"        c.wallet_id as wallet_id, \n" +
					"        a.activity_code as activity_code,\n" +
					"		 a.activity_value * nvl(b.multiplier,1) as POINTS_EARNED, \n" +
					"        a.u_id as u_id\n" +
					"from    brands_loyalty_programs_to_activities a, \n" +
					"        tiered_loyalty_programs b, \n" +
					"        customer_wallet_to_customers_brands c\n" +
					"where   a.brand_id = ?\n" +
					"        and a.activity_code = ?\n" +
					"        and c.brand_id = a.brand_id\n" +
					"        and c.customer_id = ?\n" +
					"        and c.brand_id = b.brand_id (+) \n" +
					"        and c.loyalty_program_id = b.loyalty_program_id (+) \n" +
					"        and c.loyalty_program_level = b.loyalty_program_level (+) \n" +
					"order by version_number desc \n" +
					"fetch next 1 rows only";

			stmt = conn.prepareStatement(query);

			stmt.setInt(1, brandId);
			stmt.setString(2, activityCode);
			stmt.setInt(3,customerId);

			ResultSet rs = stmt.executeQuery();

			if (rs == null || !rs.next()) {
				return ;
			}

			int cId = rs.getInt(1);
			int bId = rs.getInt(2);
			int lpId = rs.getInt(3);
			int wId = rs.getInt(4);
			String actCode = rs.getString(5);
			int ptsEarned = rs.getInt(6);
			int uId = rs.getInt(7);


			long millis = System.currentTimeMillis();
			Date date = new java.sql.Date(millis);

			String selectQuery = "select points_earned from customers_to_blp_activities where customer_id=? and brand_id=? and " +
					" activity_code=? and performed_date = to_char( ? , 'DD-MON-yy') ";

			stmt = conn.prepareStatement(selectQuery);

			stmt.setInt(1, customerId);
			stmt.setInt(2, brandId);
			stmt.setString(3, "ACT101");
			stmt.setDate(4,java.sql.Date.valueOf(java.time.LocalDate.now()));


			rs = stmt.executeQuery();


			if(rs!= null && rs.next())
			{
				String updateQuery = "update customers_to_blp_activities(points_earned = ?) where brand_id = ? and customer_id = ? "+
						"and activity_code = ? and performed_date = ?";

				stmt = conn.prepareStatement(updateQuery);

				stmt.setInt(1,rs.getInt("points_earned") + ptsEarned);
				stmt.setInt(2,customerId);
				stmt.setInt(3,brandId);
				stmt.setString(4,"ACT101");
				stmt.setDate(5,date);

				stmt.executeUpdate();


			}
			else {
				String insertQuery = "insert into customers_to_blp_activities(customer_id,brand_id,loyalty_program_id,wallet_id,activity_code,points_earned,performed_date,u_id) \n" +
						"values(?,?,?,?,?,?,?,?)";


				stmt = conn.prepareStatement(insertQuery);

				stmt.setInt(1, cId);
				stmt.setInt(2, bId);
				stmt.setInt(3, lpId);
				stmt.setInt(4, wId);
				stmt.setString(5, actCode);
				stmt.setInt(6, ptsEarned);
				stmt.setDate(7,date);
				stmt.setInt(8,uId);

				stmt.executeUpdate();
			}



			return ;

		}
		catch (Exception ex){
			System.out.println(ex.getMessage()+" "+ex.toString());
		}

		return ;
	}


	public static ResultSet getAllActivityTypes() throws Exception {
		String query = "select ACTIVITY_CODE,ACTIVITY_NAME from ACTIVITIES";
		stmt = conn.prepareStatement(query);
		rs = stmt.executeQuery();
		return rs;

	}

	public static ResultSet getBrandRecord(Login login) throws Exception {
		String userTableQuery = "select USER_NAME, NAME, LOGIN_TYPE from users " + "where USER_NAME = ?";
		String query = "select BRAND_ID from brands " + "where NAME= ?";
		try {
			stmt = conn.prepareStatement(userTableQuery);

			stmt.setString(1, login.getUserName());

			rs = stmt.executeQuery();

			if (rs == null || !rs.next()) {
				System.out.println("Error in retrieving user record !!!!");
				return rs;
			}
			String brandName = rs.getString("NAME");

			stmt = conn.prepareStatement(query);

			stmt.setString(1, brandName);

			rs = stmt.executeQuery();

			return rs;

		} catch (Exception e) {
			return null;
		}

	}

	public static void insertTierProgramRec(TierLoyaltyProgram tierLoyaltyProgram) throws Exception {
		String query = "insert into TIERED_LOYALTY_PROGRAMS(BRAND_ID,LOYALTY_PROGRAM_ID,POINTS_REQUIRED,LOYALTY_PROGRAM_LEVEL,MULTIPLIER)  values (?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, tierLoyaltyProgram.getBrandId());

			stmt.setInt(2, tierLoyaltyProgram.getLoyaltyProgramId());

			stmt.setString(3, tierLoyaltyProgram.getPointsRequired());

			stmt.setString(4, tierLoyaltyProgram.getLoyaltyProgramLevel());

			stmt.setInt(5, tierLoyaltyProgram.getMultiplier());

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}

	}

	public static void insertRegularProgramRec(RegularLoyaltyProgram regularLoyaltyProgram) throws Exception {
		String query = "insert into REGULAR_LOYALTY_PROGRAMS(BRAND_ID)  values (?)";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, regularLoyaltyProgram.getBrandId());

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}

	}

	public static ResultSet getRegularProgramRec(Integer brandId) throws Exception {
		String query = "select LOYALTY_PROGRAM_ID from REGULAR_LOYALTY_PROGRAMS " + "where BRAND_ID= ?";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, brandId);

			rs = stmt.executeQuery();

			return rs;

		} catch (Exception e) {
			return null;
		}

	}

	public static ResultSet getTierProgramRec(Integer brandId) throws Exception {
		String query = "select LOYALTY_PROGRAM_ID from TIERED_LOYALTY_PROGRAMS " + "where BRAND_ID= ?";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, brandId);

			rs = stmt.executeQuery();

			return rs;

		} catch (Exception e) {
			return null;
		}

	}

	public static void insertBrandLPActivtiesRec(String activityCode, Integer activityValue, Integer versionNumber)
			throws Exception {
		String query = "insert into BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES(BRAND_ID,LOYALTY_PROGRAM_ID,ACTIVITY_CODE, ACTIVITY_VALUE, VERSION_NUMBER)  values (?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, AppData.brandId);

			stmt.setInt(2, AppData.regularLoyaltyProgramId);

			stmt.setString(3, activityCode);

			stmt.setInt(4, activityValue);

			stmt.setInt(5, versionNumber);

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}

	}

	public static ResultSet getAllRewardCategories() throws SQLException {
		String query = "select REWARD_CATEGORY_CODE, REWARD_NAME from REWARD_CATEGORIES";
		stmt = conn.prepareStatement(query);
		rs = stmt.executeQuery();
		return rs;
	}

	private static Integer getCurrentActivityValue(String activityCode) throws Exception {
		String query = "select ACTIVITY_VALUE from BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES "
				+ "where ACTIVITY_CODE=? AND BRAND_ID=? ";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setString(1, activityCode);

			stmt.setInt(2, AppData.brandId);

			rs = stmt.executeQuery();

			if (rs == null || !rs.next()) {
				System.out.println("Error in retrieving current activity record !!!!");
				return -1;
			} else
				return rs.getInt("ACTIVITY_VALUE");

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}

	}

	private static void updateCurrentActivityValue(String activityCode, Integer value) throws Exception {
		String query = "UPDATE BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES SET ACTIVITY_VALUE=? "
				+ "where ACTIVITY_CODE=? AND BRAND_ID=? ";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, value);

			stmt.setString(2, activityCode);

			stmt.setInt(3, AppData.brandId);

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}
	}

	public static void insertBrandLPRewardsRec(String rewardCategoryCode, Integer noofInstances, String rewardValue,
											   Integer versionNumber, Date expiryDate) throws Exception {
		String query = "insert into BRANDS_LOYALTY_PROGRAMS_TO_REWARDS(BRAND_ID,LOYALTY_PROGRAM_ID,REWARD_CATEGORY_CODE, NUMBER_OF_INSTANCES, REWARD_VALUE, VERSION_NUMBER, EXPIRY_DATE)  values (?,?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, AppData.brandId);

			stmt.setInt(2, AppData.regularLoyaltyProgramId);

			stmt.setString(3, rewardCategoryCode);

			stmt.setInt(4, noofInstances);

			stmt.setString(5, rewardValue);

			stmt.setInt(6, versionNumber);

			stmt.setDate(7, expiryDate);

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}

	}

	public static ResultSet getActivitiesofBrand() throws SQLException {
		String query = "SELECT t2.ACTIVITY_CODE,t2.ACTIVITY_NAME FROM BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES t1,ACTIVITIES t2 WHERE t1.ACTIVITY_CODE=t2.ACTIVITY_CODE and BRAND_ID=?";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, AppData.brandId);
		rs = stmt.executeQuery();
		return rs;

	}

	private static Integer getMaxVersionNumberOfActivity(String activityCode) throws Exception {

		String query = "select MAX(VERSION_NUMBER) as MAX_VERSION from BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES "
				+ "where ACTIVITY_CODE=? AND BRAND_ID=? ";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setString(1, activityCode);

			stmt.setInt(2, AppData.brandId);

			rs = stmt.executeQuery();

			if (rs == null || !rs.next()) {
				System.out.println("Error in retrieving current activity record !!!!");
				return -1;
			} else
				return rs.getInt("MAX_VERSION");

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}
	}

	public static void addActivityInBLPA(String activityCode, Integer value) throws Exception {
		Integer curValue = getCurrentActivityValue(activityCode);
		if (curValue == -1) {
			updateCurrentActivityValue(activityCode, value);
			return;
		} else {
			System.out.println("Rule already existing. Please select update option to change");
			return;
		}
	}

	public static void updateActivityInBLPA(String activityCode, Integer value) throws Exception {
		Integer curValue = getCurrentActivityValue(activityCode);
		if (curValue == -1) {
			System.out.println("Rule is not existing. Please select add option.");
			return;
		} else {
			Integer maxVersion = getMaxVersionNumberOfActivity(activityCode);
			insertBrandLPActivtiesRec(activityCode, value, maxVersion + 1);
			return;
		}
	}

	public static ResultSet getRewardCategoriesofBrand() throws SQLException {
		String query = "SELECT DISTINCT t2.REWARD_CATEGORY_CODE,t2.REWARD_NAME FROM BRANDS_LOYALTY_PROGRAMS_TO_REWARDS t1,REWARD_CATEGORIES t2 WHERE t1.REWARD_CATEGORY_CODE=t2.REWARD_CATEGORY_CODE and BRAND_ID=?";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, AppData.brandId);
		rs = stmt.executeQuery();
		return rs;

	}

	public static void addRewardCategoryInBLPC(String rewardCategoryCode, String value) throws Exception {
		ResultSet rs = getCurrentRewardValue(rewardCategoryCode);
		if (rs == null || !rs.next()) {
			System.out.println("Error in retrieving current reward category record !!!!");
			return;
		}
		String curValue = rs.getString("REWARD_VALUE");
		if ("-1".equals(curValue)) {
			updateRewardCategoryValue(rewardCategoryCode, value);
			return;
		} else {
			System.out.println("Rule already existing. Please select update option to change");
			return;
		}

	}

	private static ResultSet getCurrentRewardValue(String rewardCategoryCode) throws Exception {
		String query = "select REWARD_VALUE,NUMBER_OF_INSTANCES, EXPIRY_DATE from BRANDS_LOYALTY_PROGRAMS_TO_REWARDS "
				+ "where REWARD_CATEGORY_CODE=? AND BRAND_ID=? ";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setString(1, rewardCategoryCode);

			stmt.setInt(2, AppData.brandId);

			rs = stmt.executeQuery();

			return rs;

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}
	}

	public static void updateRewardCategoryValue(String rewardCategoryCode, String value) throws Exception {
		String query = "UPDATE BRANDS_LOYALTY_PROGRAMS_TO_REWARDS SET REWARD_VALUE=? "
				+ "where REWARD_CATEGORY_CODE=? AND BRAND_ID=? ";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setString(1, value);

			stmt.setString(2, rewardCategoryCode);

			stmt.setInt(3, AppData.brandId);

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}

	}

	public static void updateRewardCategoryInBLPC(String rewardCategoryCode, String value) throws Exception {
		ResultSet rs = getCurrentRewardValue(rewardCategoryCode);
		if (rs == null || !rs.next()) {
			System.out.println("Error in retrieving current reward category record !!!!");
			return;
		}
		String curValue = rs.getString("REWARD_VALUE");
		Integer noofInstances = rs.getInt("NUMBER_OF_INSTANCES");
		Date expiryDate = rs.getDate("EXPIRY_DATE");
		if ("-1".equals(curValue)) {
			System.out.println("Rule is not existing. Please select add option.");
			return;
		} else {
			Integer maxVersion = getMaxVersionNumberOfRewardCategory(rewardCategoryCode);
			insertBrandLPRewardsRec(rewardCategoryCode, noofInstances, value, maxVersion + 1, expiryDate);
			return;
		}

	}

	private static Integer getMaxVersionNumberOfRewardCategory(String rewardCategoryCode) throws Exception {
		String query = "select MAX(VERSION_NUMBER) as MAX_VERSION from BRANDS_LOYALTY_PROGRAMS_TO_REWARDS "
				+ "where REWARD_CATEGORY_CODE=? AND BRAND_ID=? ";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setString(1, rewardCategoryCode);

			stmt.setInt(2, AppData.brandId);

			rs = stmt.executeQuery();

			if (rs == null || !rs.next()) {
				System.out.println("Error in retrieving current activity record !!!!");
				return -1;
			} else
				return rs.getInt("MAX_VERSION");

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}
	}

	public static void updateBrandsTypeToTiered() throws Exception {
		String query = "UPDATE BRANDS SET LOYALTY_TYPE=? " + "where BRAND_ID=? ";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setString(1, "TIERED");

			stmt.setInt(2, AppData.brandId);

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}
	}

	public static Integer getTieredRecords() throws Exception {
		String query = "select count(*) as count_tiers from TIERED_LOYALTY_PROGRAMS " + "where BRAND_ID=? ";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, AppData.brandId);

			rs = stmt.executeQuery();

			if (rs == null || !rs.next()) {
				return 0;
			} else
				return rs.getInt("count_tiers");

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}

	}

	public static ResultSet validateBLPA() throws Exception {
		String query = "select ACTIVITY_VALUE from BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES " + "where BRAND_ID=? ";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, AppData.brandId);

			rs = stmt.executeQuery();

			return rs;

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}
	}

	public static ResultSet validateBLPRC() throws Exception {
		String query = "select REWARD_VALUE from BRANDS_LOYALTY_PROGRAMS_TO_REWARDS " + "where BRAND_ID=? ";
		try {
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, AppData.brandId);

			rs = stmt.executeQuery();

			return rs;

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}
	}

	public static void insertRegularLoyaltyData(String query, RegularLoyaltyProgram regularLPObj) throws Exception {
		try {

			stmt = conn.prepareStatement(query);

			stmt.setInt(1, regularLPObj.getBrandId());

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());

			throw new Exception(e);
		}
	}
=======
	}	
//	
//	public static void insertBrand(String query, Brand brand)
//	{
//		try
//		{
//			stmt = conn.prepareStatement(query);
//			
//			stmt.setLong(1, brand.getBrandId());
//			
//			stmt.setString(2, brand.getName());
//			
//			stmt.setString(3, brand.getAddress());
//			
//			stmt.setDate(4, brand.getJoinDate());
//			
//			stmt.executeUpdate();
//			
//		}
//		catch(Exception e)
//		{
//			return;
//		}
//	}
//	
//	public static void insertCustomer(String query, Customer custObj)
//	{
//		try
//		{
//			stmt = conn.prepareStatement(query);
//			
//			stmt.setInt(1, custObj.getCustomerId());
//			
//			stmt.setString(2, custObj.getName());
//			
//			stmt.setString(3, custObj.getAddress());
//			
//			stmt.setInt(4, custObj.getPhoneNumber());
//			
//			stmt.executeUpdate();
//			
//		}
//		catch(Exception e)
//		{
//			return;
//		}
//	}
>>>>>>> df031a0a9c777117d072d057b184f6e421d09db4
>>>>>>> c109390a77226e8e443ba62bf3b6f2144e9dfd65
}
