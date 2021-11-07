package brands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBTasks;
import util.DisplayOptions;

public class BrandActivities {
	public void takeInput() {
		List<String> activityTypesChosen = new ArrayList<>();
		// List<Integer> activityValues = new ArrayList<>();
		ResultSet activities = null;
		List<String> activitiesAvailable = new ArrayList<>();
		List<String> activitieNamesAvailable = new ArrayList<>();
		try {
			activities = DBTasks.getAllActivityTypes();
			while (activities.next()) {
				activitiesAvailable.add(activities.getString("ACTIVITY_CODE"));
				activitieNamesAvailable.add(activities.getString("ACTIVITY_NAME"));
			}
			activitieNamesAvailable.add("Go back");
		} catch (Exception e) {
			System.out.println("Error while fetching all activities");
			return;
		}
		while (true) {

			DisplayOptions.printOptions(activitieNamesAvailable);
			int option = DisplayOptions.getSc().nextInt();

			if (option < 1 || option > activitiesAvailable.size() + 1) {
				System.out.println("Invalid Option");
				continue;
			}

			for (int i = 0; i < activitiesAvailable.size() + 1; i++) {
				if (option - 1 == activitiesAvailable.size()) {
					for (int j = 0; j < activityTypesChosen.size(); j++) {
						try {

							DBTasks.insertBrandLPActivtiesRec(activityTypesChosen.get(j), -1, 1);
						} catch (Exception e) {
							System.out.println("Error inserting record in BLPA table");
							return;
						}
					}
					return;
				} else {
					if (activityTypesChosen.contains(activitiesAvailable.get(option - 1))) {
						System.out.println("Already chosen. Please select another or select Go back ");
						break;
					} else {
						// System.out.println("Enter activity value: ");
						// Integer value = DisplayOptions.getSc().nextInt();
						activityTypesChosen.add(activitiesAvailable.get(option - 1));
						// activityValues.add(value);
						break;
					}
				}
			}

//			switch (option) {
//			case 1: {
//				if (activityTypesChosen.contains(activitiesAvailable.get(0))) {
//					System.out.println("Already chosen. Please select another or select Go back ");
//					continue;
//				} else {
//					activityTypesChosen.add(activitiesAvailable.get(0));
//				}
//				break;
//			}
//			case 2: {
//				if (activityTypesChosen.contains(activitiesAvailable.get(1))) {
//					System.out.println("Already chosen. Please select another or select Go back ");
//					continue;
//				} else {
//					activityTypesChosen.add(activitiesAvailable.get(1));
//				}
//				break;
//			}
//			case 3: {
//				if (activityTypesChosen.contains(activitiesAvailable.get(2))) {
//					System.out.println("Already chosen. Please select another or select Go back ");
//					continue;
//				} else {
//					activityTypesChosen.add(activitiesAvailable.get(2));
//				}
//				break;
//			}
//			case 4: {
//				// add selected activities in table
//				for (int i = 0; i < activityTypesChosen.size(); i++) {
//					try {
//						DBTasks.insertBrandLPActivtiesRec(activityTypesChosen.get(i));
//					} catch (Exception e) {
//						System.out.println("Error inserting record in BLPA table");
//						return;
//					}
//				}
//				break;
//			}
//			}
		}

	}

	public void addOrUpdateRERules(Integer opt) {
		try {
			ResultSet rs = DBTasks.getActivitiesofBrand();
			List<String> activityNames = new ArrayList<>();
			List<String> activityCodes = new ArrayList<>();
			while (rs.next()) {
				activityCodes.add(rs.getString("ACTIVITY_CODE"));
				activityNames.add(rs.getString("ACTIVITY_NAME"));
			}
			activityNames.add("Go back");
			while (true) {
				DisplayOptions.printOptions(activityNames);
				int option = DisplayOptions.getSc().nextInt();

				if (option < 1 || option > activityNames.size()) {
					System.out.println("Invalid Option");
					continue;
				}

				// for (int i = 0; i < activityNames.size(); i++) {
				if (option  == activityNames.size()) {
					return;
				} else {
					System.out.println("Enter activity value: ");
					Integer value = DisplayOptions.getSc().nextInt();
					try {
						if (opt == 1)
							DBTasks.addActivityInBLPA(activityCodes.get(option - 1), value);
						else if (opt == 2)
							DBTasks.updateActivityInBLPA(activityCodes.get(option - 1), value);
					} catch (Exception e) {
						System.out.println("Error in updating the activity value: " + e);
						continue;
					}
				}
				// }

			}
		} catch (SQLException e) {
			System.out.println("Error while retrieving activities chosen by the brand");
			return;
		}

	}
}
