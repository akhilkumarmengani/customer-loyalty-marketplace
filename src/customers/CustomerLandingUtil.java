package customers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBTasks;

import util.AppData;
import util.DisplayOptions;


class BrandItem{
	int index;
	int brandId;
	String brandName;
	BrandItem(int index,int brandId, String brandName){
		this.index = index;
		this.brandId = brandId;
		this.brandName = brandName;
	}
}

public class CustomerLandingUtil {
	
	
	public static void displayBrands(List<BrandItem> brandList) {
		int  index = 0;
		while(index < brandList.size()) {
			BrandItem brand = brandList.get(index);
			System.out.println(brand.index +". "+ brand.brandName);
			index++;
		}
	}
	
	
	public static String enrollInLoyaltyProgram() throws Exception {
		//ShowAllBrands
		//Select A Brand
		//Create Entry in Customer to Brands
		String name = null;
		ResultSet rs = DBTasks.getAllBrandLoyaltyPrograms();
		//System.out.println(rs.getFetchSize());
		List<BrandItem> brandList = new ArrayList<BrandItem>();
		int index = 1;
		
		if (!rs.next()) { 
			System.out.println("No Brands Exists");
			return null;
		} 
		else 
		{ 
			do { 
				
				//System.out.println(rs.getString("BRAND_ID"));
				//1System.out.println(rs.getString("NAME"));
				int brandId = Integer.valueOf(rs.getString("BRAND_ID"));
				String lpType = rs.getString("LOYALTY_TYPE");
				int lpId = DBTasks.getLoyaltyId(brandId);

				AppData.regularLoyaltyProgramId = lpId;
				AppData.brandId = brandId;
				AppData.tieredLoyaltyProgram = "TIERED".equals(lpType)? true:false;
				if(validate()){
					brandList.add(new BrandItem(index, Integer.valueOf(rs.getString("BRAND_ID")), rs.getString("NAME")));
					index++;
				}

			} while (rs.next()); 
		}
		
		displayBrands(brandList);
		
		System.out.println("Please Enter Loyalty Program You Want to Enroll In!");
		
		while(true) {
			
			int option = DisplayOptions.getSc().nextInt();
			if(!validateInput(option,1,brandList.size())) {
				System.out.println("Please Enter Valid Brand Name!");
				displayBrands(brandList);
			}
			else {
				
				name = brandList.get(option-1).brandName; 
				int customerId = AppData.customerId;
				int brandId = brandList.get(option-1).brandId;
				String brandName = brandList.get(option-1).brandName;
				if(!isEnrolledInLoyaltyProgram(customerId,brandId)) {
					DBTasks.insertIntoCustomersToBrands(customerId,brandId);
				}
				else {
					System.out.println("You are already enrolled in this Brand Loyalty Program : "+brandName);
					return null;
				}
				break;
			}
		}

		return name;
		
	}
	
	public static void getRewardActivities() {
		//Query in CustomersToBLPActivities
	}
	
	public static void viewWallet() {
		//Query in CustomerWalletToCustomersBrands
	}
	
	public static void redeemPoints() {
		// 
	}
	
	public static boolean validateInput(int value, int start, int end) {
		if(value < start || value > end)
			return false;
		return true;
	}
	
	public static int getCustomerIdByUserId(int userId) throws Exception {
		Integer result = DBTasks.getCustomerIdByUserId(userId);
		return result==null? -1:result;
	}
	
	
	public static boolean isEnrolledInLoyaltyProgram(int customerId, int brandId) throws SQLException {
		return DBTasks.isEnrolledInLoyaltyProgram(customerId,brandId);
	}
	
	public static int displayBrandLoyaltyPrograms(int customerId) throws SQLException {
		List<String[]> brandList = DBTasks.getBrandLoyaltyPrograms(customerId);
		for(int i = 0 ; i < brandList.size(); i++) {
			System.out.println((i+1) +". "+ brandList.get(i)[1]);
		}
		
		if(brandList.size()==0) {
			System.out.println("Please Go Back and Enroll in Loyalty Programs!");
			return -1;
		}
	
		while(true) {
			int option = DisplayOptions.getSc().nextInt();
			if(!CustomerLandingUtil.validateInput(option, 1, brandList.size()))
			{
				System.out.println("Please Enter a Valid Input!");
				continue;
			}
			return Integer.parseInt(brandList.get(option-1)[0]);
		}
		
	}
	
	
	public static List<String[]> getActivitiesForBrand(int brandId) throws SQLException {
		return DBTasks.getActivitiesForBrand(brandId);
	}
	
	public static void displayActivities(List<String[]> activities) {
		
		
	}
	
	
	public static void getGiftCardsForCustomerAndBrand(int customerId, int brandId) {
		//return DBTasks.getGiftCardsForCustomerAndBrand(customerId,brandId);
	}

//	public static List<String[]> getAllGiftCardsOfCustomer(int customerId, int brandId){
//		List<String[]> list = DBTasks.getAllGiftCardsOfCustomer(customerId,brandId);
//		return list;
//	}

	public static void insertIntoCustomerToBLPActivities(int customerId,int brandId, String activityCode, int giftCardValue, int numberOfInstances) throws SQLException {
		DBTasks.insertIntoCustomerToBLPActivities(customerId,brandId,activityCode,giftCardValue,numberOfInstances);
	}

	public static List<String[]> getAllCurrentRewardsForABrand(int brandId)
	{
		List<String[]> list = DBTasks.getAllCurrentRewardsForABrand(brandId);
		return list;
	}
	
	public static int getTotalWalletPointsForACustomerInBrand(int brandId, int customerId)
	{
		return DBTasks.getTotalWalletPointsForACustomerInBrand(customerId,brandId);
	}

	public static void updateCustomerRewardsForABrand(int customerId, int brandId, int[] totalRewards,int[] totalRedeemedPoints, int[] uid)
	{
		DBTasks.updateCustomerRewardsForABrand(customerId,brandId,totalRewards, totalRedeemedPoints, uid);
	}

	public static void displayWallet(int customerId){
		DBTasks.displayWallet(customerId);
	}

	public static void insertIntoCustomerToBLPActivitiesForReview(int customerId, int brandId, String activityCode){
		DBTasks.insertIntoCustomerToBLPActivitiesForReview(customerId, brandId, activityCode);
	}


	public static List<String[]> getGiftCardsForCustomer(int customerId, int brandId){
		List<String[]> list = DBTasks.getGiftCardsForCustomer(customerId,brandId);
		return list;
	}


	public static boolean validate() {
		try {
			Integer loyaltyProgram = null;
			if (AppData.tieredLoyaltyProgram) {
				loyaltyProgram = 1;
			} else {
				if (AppData.regularLoyaltyProgramId != null) {
					loyaltyProgram = 0;
				}
			}
			if (loyaltyProgram != null) {
				boolean rewardFlag = false;
				boolean activityFlag = false;
				if (loyaltyProgram == 1) {
					if (DBTasks.getTieredRecords() == 0) {
						//System.out.println(
						//		"Loyalty program is not valid. The tiered program doesnt have any tiers added");
						return false;
					}
				}
				ResultSet rs = DBTasks.validateBLPA();
				if (rs == null || !rs.next()) {
					//System.out.println(
					//		"Loyalty program is not valid. The Loyalty program doesnt have any activities associated with it");
					return false;
				} else {
					activityFlag = false;
					do {
						if (rs.getInt("ACTIVITY_VALUE") != -1) {
							activityFlag = true;
							break;
						}
					} while (rs.next());
					if (activityFlag == false) {
//						System.out.println(
//								"Loyalty program is not valid. The Loyalty program doesnt have any rule added to any activity");
						return false;
					}
				}

				rs = DBTasks.validateBLPRC();
				if (rs == null || !rs.next()) {
//					System.out.println(
//							"Loyalty program is not valid. The Loyalty program doesnt have any reward categories associated with it");
					return false;
				} else {
					rewardFlag = false;
					do {
						if (!"-1".equals(rs.getString("REWARD_VALUE"))) {
							rewardFlag = true;
							break;
						}
					} while (rs.next());
					if (rewardFlag == false) {
//						System.out.println(
//								"Loyalty program is not valid. The Loyalty program doesnt have any redeeming rule added");
						return false;
					}
				}

				if (activityFlag && rewardFlag) {
					//System.out.println("Loyalty program is valid.");
					return true;
				}

			} else {
				//System.out.println("Loyalty program is not valid. Please ensure it has everything setup");
				return false;
			}
		} catch (Exception e) {
			//System.out.println("Error in validation: " + e);
			return false;
		}
		return true;
	}

}
