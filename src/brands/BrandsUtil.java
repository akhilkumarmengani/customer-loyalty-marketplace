package brands;

import java.sql.ResultSet;

import db.DBTasks;
import util.AppData;
import util.Login;

public class BrandsUtil {

	public static void initializeAllFields(Login login) throws Exception {
		ResultSet resultSet = DBTasks.getBrandRecord(login);

		if (resultSet == null || !resultSet.next()) {
			System.out.println("No brand record found!!!!");
		} else {
			Integer brandId = Integer.parseInt(resultSet.getString("BRAND_ID"));
			AppData.brandId = brandId;
		}
		resultSet = DBTasks.getRegularProgramRec(AppData.brandId);
		if (resultSet != null && resultSet.next()) {
			AppData.regularLoyaltyProgramId = resultSet.getInt("LOYALTY_PROGRAM_ID");
		}
		resultSet = DBTasks.getTierProgramRec(AppData.brandId);
		if (resultSet != null && resultSet.next()) {
			//System.out.println(resultSet.getInt("LOYALTY_PROGRAM_ID"));
			AppData.tieredLoyaltyProgram = true;
		}
	}
}
