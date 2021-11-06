package customers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBTasks;

import models.Brand;
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
		List<BrandItem> brandList = new ArrayList<BrandItem>();
		int index = 1;
		
		if (!rs.next()) { 
			System.out.println("No Brands Exists");
			return null;
		} 
		else 
		{ 
			do { 
				brandList.add(new BrandItem(index, Integer.valueOf(rs.getString("BRAND_ID")), rs.getString("NAME")));
				index++;
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
				name = brandList.get(option).brandName; 
				DBTasks.insertIntoCustomersToBrands(AppData.customerId,brandList.get(option).brandId);
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
	
	
	
}
