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
		ResultSet activities = null;
		List<String> activitiesAvailable = new ArrayList<>();
		List<String> activitieNamesAvailable = new ArrayList<>();
		try {
			activities = DBTasks.getAllActivityTypes();
			while (activities.next()) {
				activitiesAvailable.add(activities.getString("ACTIVITY_CODE"));
				activitieNamesAvailable.add(activities.getString("ACTIVITY_NAME"));
			}
			ResultSet rs = DBTasks.getActivitiesofBrand();
			while (rs.next()) {
				activitiesAvailable.remove(rs.getString("ACTIVITY_CODE"));
				activitieNamesAvailable.remove(rs.getString("ACTIVITY_NAME"));
			}
			if (activitiesAvailable.size() == 0) {
				System.out.println("All activity types already added");
				return;
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
					continue;
				} else {
					activityTypesChosen.add(activitiesAvailable.get(option - 1));
					continue;
				}
			}

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
			if (activityCodes.size() == 0) {
				System.out.println("Please add atleast one activity type to add an RE rule");
				return;
			}
			activityNames.add("Go back");
			while (true) {
				DisplayOptions.printOptions(activityNames);
				int option = DisplayOptions.getSc().nextInt();

				if (option < 1 || option > activityNames.size()) {
					System.out.println("Invalid Option");
					continue;
				}

				if (option == activityNames.size()) {
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

			}
		} catch (SQLException e) {
			System.out.println("Error while retrieving activities chosen by the brand");
			return;
		}

	}
}
