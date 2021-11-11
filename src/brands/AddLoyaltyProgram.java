package brands;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBTasks;
import models.RegularLoyaltyProgram;
import models.TierLoyaltyProgram;
import util.AppData;
import util.DisplayOptions;

public class AddLoyaltyProgram {

	public boolean addRegularUtil() {
		if (AppData.regularLoyaltyProgramId != null) {
			return false;
		}
		RegularLoyaltyProgram regularLoyaltyProgram = new RegularLoyaltyProgram(AppData.brandId);
		try {
			DBTasks.insertRegularProgramRec(regularLoyaltyProgram);
		} catch (Exception e) {
			System.out.println("Error adding record in Regular loyalty prog table: " + e);
			return false;
		}

		// Get regular loyalty program record ID after inserting that above.
		try {
			ResultSet regularRecSet = DBTasks.getRegularProgramRec(AppData.brandId);
			if (regularRecSet == null || !regularRecSet.next()) {
				System.out.println("Error in retrieving Regular loyalty record !!!!");
				return false;
			}
			AppData.regularLoyaltyProgramId = regularRecSet.getInt("LOYALTY_PROGRAM_ID");

		} catch (Exception e) {
			System.out.println("Error getting record from Regular loyalty prog table: " + e);
			return false;
		}
		return true;
	}

	public void addRegular() {
		if (AppData.regularLoyaltyProgramId != null && AppData.tieredLoyaltyProgram == true) {
			System.out.println("Tiered loyalty program already added. Please Choose Tiered option");
			return;
		}
		addRegularUtil();
		while (true) {
			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.Regular));
			int option = DisplayOptions.getSc().nextInt();

			if (option < 1 || option > 3) {
				System.out.println("Invalid Regular loyalty program Option");
				continue;
			}

			switch (option) {
				case 1: {
					new BrandActivities().takeInput();
					break;
				}
				case 2: {
					new BrandRewards().takeInput();
					break;
				}
				case 3: {
					return;
				}
			}
		}
	}

	public void addTier() {
		if (AppData.regularLoyaltyProgramId != null && AppData.tieredLoyaltyProgram == false) {
			System.out.println("Regular loyalty program already added. Please Choose Regular option");
			return;
		}
		while (true) {
			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.Tier));
			int option = DisplayOptions.getSc().nextInt();

			if (option < 1 || option > 4) {
				System.out.println("Invalid Tier Option");
				continue;
			}

			switch (option) {
				case 1: {
					DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.TiersSetup));
					int tierOption = DisplayOptions.getSc().nextInt();

					if (tierOption < 1 || tierOption > 2) {
						System.out.println("Invalid Tier setup Option");
						continue;
					}

					switch (tierOption) {
						case 1: {
							if (AppData.tieredLoyaltyProgram != false) {
								System.out.println("Tiered loyalty program already set. Please choose other option");
								continue;
							}
							System.out.println("Enter Number of tiers");
							int noofTiers = DisplayOptions.getSc().nextInt();
							addRegularUtil();
							if (noofTiers == 0) {
								AppData.tieredLoyaltyProgram = true;
								try {
									DBTasks.updateBrandsTypeToTiered();
								} catch (Exception e) {
									System.out.println("Error while updating loyalty type for brand in brands table: " + e);
								}
								continue;
							}
							System.out.println("Name of the tiers (in increasing order of precedence)");
							List<String> tierNamesList = new ArrayList<>();
							for (int i = 0; i < noofTiers; i++) {
								tierNamesList.add(DisplayOptions.getSc().next());
							}
							System.out.println("Points required for each tier");
							List<String> tierLevelPoints = new ArrayList<>();
							for (int i = 0; i < noofTiers; i++) {
								tierLevelPoints.add(DisplayOptions.getSc().next());
							}
							System.out.println("Multiplier for each tier");
							List<Integer> multipliers = new ArrayList<>();
							for (int i = 0; i < noofTiers; i++) {
								multipliers.add(DisplayOptions.getSc().nextInt());
							}

							for (int i = 0; i < noofTiers; i++) {
								TierLoyaltyProgram tierLoyaltyProgram = new TierLoyaltyProgram(AppData.brandId,
										AppData.regularLoyaltyProgramId, tierLevelPoints.get(i), tierNamesList.get(i),
										multipliers.get(i));
								try {
									DBTasks.insertTierProgramRec(tierLoyaltyProgram);
								} catch (Exception e) {
									System.out.println(e);
								}
							}

							try {
								ResultSet rs = DBTasks.getTierProgramRec(AppData.brandId);
								if (rs == null || !rs.next()) {
									System.out.println("Error in retrieving Tier loyalty record !!!!");
									continue;
								}
								AppData.regularLoyaltyProgramId = rs.getInt("LOYALTY_PROGRAM_ID");
							} catch (Exception e) {
								System.out.println(e);
							}
							System.out.println("Tiered loyalty program added!!");
							break;
							// Add these details in tier table
						}
						case 2: {
							continue;
						}
					}

					break;
				}
				case 2: {
					new BrandActivities().takeInput();
					break;
				}
				case 3: {
					new BrandRewards().takeInput();
					break;
				}
				case 4: {
					return;
				}
			}
		}
	}
}
