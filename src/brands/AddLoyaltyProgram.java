package brands;

import util.DisplayOptions;

public class AddLoyaltyProgram {

	public void addRegular() {
		// TODO Auto-generated method stub
		System.out.println("Will have to add Regular loyalty program");

	}

	public void addTier() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("Will have to add Tier loyalty program");
			DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.Tier));
			int option = DisplayOptions.getSc().nextInt();

			if (option < 1 || option > 4) {
				System.out.println("Invalid Option");
				continue;
			}
			
			switch (option) {
				case 1: {
					DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.TiersSetup));
					break;
				}
				case 2:{
					DisplayOptions.printOptions(DisplayOptions.options.get(DisplayOptions.ActivityTypes));
					break;
				}
				case 3:{
					break;
				}
				case 4:{
					break;
				}
			}
		}
	}
}
