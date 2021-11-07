package util;

public class AppData {
	
	
	// Customer Data
	public static Integer customerId = null;
	public static String customerName = null;
	public static String customerUserName = null;

	
	// User Data
	public static Integer userId = null;


	// Brand Data
	public static Integer brandId = null;
	public static Integer regularLoyaltyProgramId = null;
	public static boolean tieredLoyaltyProgram = false;


	public static void clearBrandVariables() {
		brandId = null;
		regularLoyaltyProgramId = null;
		tieredLoyaltyProgram = false;
	}
	
	

}
