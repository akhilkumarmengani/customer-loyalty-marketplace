package models;

public class RegularLoyaltyProgram {
	public Integer BrandId;
	public Integer LoyaltyProgramID;

	public RegularLoyaltyProgram(Integer brandId) {
		this.BrandId = brandId;
	}

	public Integer getBrandId() {
		return BrandId;
	}

	public void setBrandId(Integer brandId) {
		BrandId = brandId;
	}

	public Integer getLoyaltyProgramID() {
		return LoyaltyProgramID;
	}

	public void setLoyaltyProgramID(Integer loyaltyProgramID) {
		LoyaltyProgramID = loyaltyProgramID;
	}

}
