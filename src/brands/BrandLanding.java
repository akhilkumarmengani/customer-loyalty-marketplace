package brands;

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
				if (option == 3)
					return;

				if (option == 1) {
					new AddLoyaltyProgram().addRegular();
				} else if (option == 2) {
					new AddLoyaltyProgram().addTier();
				}
				break;
			}
			case 2: {

			}
			case 3: {

			}
			case 4: {

			}
			case 5: {

			}
			case 6: {

			}
			case 7: {

			}

			}
		}
	}

}
