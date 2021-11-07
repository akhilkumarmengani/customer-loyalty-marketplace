package brands;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBTasks;
import util.DisplayOptions;

public class BrandRewards {

	public void takeInput() {
		List<String> rewardCategoriesChosen = new ArrayList<>();
		List<Integer> rewardCategoriesInstances = new ArrayList<>();
		List<Date> rewardCategoriesExpiryDates = new ArrayList<>();

		ResultSet rewardCategories = null;
		List<String> rewardCategoriesAvailable = new ArrayList<>();
		List<String> rewardCategoriesNamesAvailable = new ArrayList<>();
		try {
			rewardCategories = DBTasks.getAllRewardCategories();
			while (rewardCategories.next()) {
				rewardCategoriesAvailable.add(rewardCategories.getString("REWARD_CATEGORY_CODE"));
				rewardCategoriesNamesAvailable.add(rewardCategories.getString("REWARD_NAME"));
			}
			rewardCategoriesNamesAvailable.add("Go back");
		} catch (Exception e) {
			System.out.println("Error while fetching all reward categories");
			return;
		}
		while (true) {
			DisplayOptions.printOptions(rewardCategoriesNamesAvailable);
			int option = DisplayOptions.getSc().nextInt();

			if (option < 1 || option > rewardCategoriesNamesAvailable.size()) {
				System.out.println("Invalid reward category Option");
				continue;
			}

			if (option - 1 == rewardCategoriesAvailable.size()) {
				for (int j = 0; j < rewardCategoriesChosen.size(); j++) {
					try {
						DBTasks.insertBrandLPRewardsRec(rewardCategoriesChosen.get(j), rewardCategoriesInstances.get(j),
								"-1", 1, rewardCategoriesExpiryDates.get(j));
					} catch (Exception e) {
						System.out.println("Error inserting record in BLPRC table");
						return;
					}
				}
				return;
			} else {
				if (rewardCategoriesChosen.contains(rewardCategoriesAvailable.get(option - 1))) {
					System.out.println("Already chosen. Please select another or select Go back ");
					continue;
				} else {
					System.out.println("Enter number of reward instances: ");
					Integer value = DisplayOptions.getSc().nextInt();
					System.out.println("Enter expiry date for the reward in yyyy-MM-dd format");
					String expriyDateString = DisplayOptions.getSc().next();
					Date expiryDate = java.sql.Date.valueOf(expriyDateString);
					rewardCategoriesChosen.add(rewardCategoriesAvailable.get(option - 1));
					rewardCategoriesInstances.add(value);
					rewardCategoriesExpiryDates.add(expiryDate);
					continue;
				}
			}
		}

	}

	public void addOrUpdateRRRules(Integer opt) {
		try {
			ResultSet rs = DBTasks.getRewardCategoriesofBrand();
			List<String> rewardCategoryNames = new ArrayList<>();
			List<String> rewardCategoryCodes = new ArrayList<>();
			while (rs.next()) {
				rewardCategoryCodes.add(rs.getString("REWARD_CATEGORY_CODE"));
				rewardCategoryNames.add(rs.getString("REWARD_NAME"));
			}
			rewardCategoryNames.add("Go back");
			while (true) {
				DisplayOptions.printOptions(rewardCategoryNames);
				int option = DisplayOptions.getSc().nextInt();

				if (option < 1 || option > rewardCategoryNames.size()) {
					System.out.println("Invalid Option");
					continue;
				}

				if (option == rewardCategoryNames.size()) {
					System.out.println("Go back option selected");
					return;
				} else {
					System.out.println("Enter Reward value: ");
					String value = DisplayOptions.getSc().next();
					try {
						if (opt == 1)
							DBTasks.addRewardCategoryInBLPC(rewardCategoryCodes.get(option - 1), value);
						else if (opt == 2)
							DBTasks.updateRewardCategoryInBLPC(rewardCategoryCodes.get(option - 1), value);
					} catch (Exception e) {
						System.out.println("Error in updating the activity value: " + e);
						continue;
					}
				}

			}
		} catch (SQLException e) {
			System.out.println("Error while retrieving activities chosen by the brand");
			return;
		}

	}

}
