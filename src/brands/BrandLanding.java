package brands;

import util.AppData;
import util.DisplayOptions;

public class BrandLanding {
	public void takeInput() {
		while (true) {
			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.BrandLanding));

			int option = DisplayOptions.getSc().nextInt();

			if (option < 1 || option > 7) {
				System.out.println("Invalid Option");
				continue;
			}

			switch (option) {
			case 1: {
				DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.AddLoyaltyProgram));

				option = DisplayOptions.getSc().nextInt();

				if (option < 1 || option > 3) {
					System.out.println("Invalid Option");
					continue;
				}
				if (option == 1) {
					new AddLoyaltyProgram().addRegular();
				} else if (option == 2) {
					new AddLoyaltyProgram().addTier();
				} else if (option == 3)
					return;
				continue;
			}
			case 2: {
				new BrandActivities().addOrUpdateRERules(1);
				continue;
			}
			case 3: {
				new BrandActivities().addOrUpdateRERules(2);
				continue;
			}
			case 4: {
				new BrandRewards().addOrUpdateRRRules(1);
				continue;
			}
			case 5: {
				new BrandRewards().addOrUpdateRRRules(2);
				continue;
			}
			case 6: {
				new BrandValidation().validate();
				continue;
			}
			case 7: {
				AppData.clearBrandVariables();
				return;
			}

			}
		}
	}

}
