package util;

public class AppData {

	// Admin Data
	public static  boolean adminLogout = false;
	
	
	// Customer Data
	public static Integer customerId = null;
	public static String customerName = null;
	public static String customerUserName = null;
	public static boolean customerLogout = false;

	
	// User Data
	public static Integer userId = null;


	// Brand Data
	public static Integer brandId = null;
	public static Integer regularLoyaltyProgramId = null;
	public static boolean tieredLoyaltyProgram = false;
	public static boolean brandLogout = false;


	public static void clearBrandVariables() {
		brandId = null;
		regularLoyaltyProgramId = null;
		tieredLoyaltyProgram = false;
	}
	
	

}
