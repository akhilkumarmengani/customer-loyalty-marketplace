package brands;

import java.sql.ResultSet;

import db.DBTasks;
import util.AppData;

public class BrandValidation {

	public void validate() {
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
						System.out.println(
								"Loyalty program is not valid. The tiered program doesnt have any tiers added");
						return;
					}
				}
				ResultSet rs = DBTasks.validateBLPA();
				if (rs == null || !rs.next()) {
					System.out.println(
							"Loyalty program is not valid. The Loyalty program doesnt have any activities associated with it");
					return;
				} else {
					activityFlag = false;
					do {
						if (rs.getInt("ACTIVITY_VALUE") != -1) {
							activityFlag = true;
							break;
						}
					} while (rs.next());
					if (activityFlag == false) {
						System.out.println(
								"Loyalty program is not valid. The Loyalty program doesnt have any rule added to any activity");
						return;
					}
				}

				rs = DBTasks.validateBLPRC();
				if (rs == null || !rs.next()) {
					System.out.println(
							"Loyalty program is not valid. The Loyalty program doesnt have any reward categories associated with it");
					return;
				} else {
					rewardFlag = false;
					do {
						if (!"-1".equals(rs.getString("REWARD_VALUE"))) {
							rewardFlag = true;
							break;
						}
					} while (rs.next());
					if (rewardFlag == false) {
						System.out.println(
								"Loyalty program is not valid. The Loyalty program doesnt have any redeeming rule added");
						return;
					}
				}

				if (activityFlag && rewardFlag) {
					System.out.println("Loyalty program is valid.");
					return;
				}

			} else {
				System.out.println("Loyalty program is not valid. Please ensure it has everything setup");
				return;
			}
		} catch (Exception e) {
			System.out.println("Error in validation: " + e);
			return;
		}
	}

}
