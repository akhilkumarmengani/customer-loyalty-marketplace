package models;

public class TierLoyaltyProgram {
	public Integer brandId;
	public Integer loyaltyProgramId;
	public String pointsRequired;
	public String loyaltyProgramLevel;
	public Integer multiplier;

	public TierLoyaltyProgram(Integer brandId, Integer loyaltyProgramId, String pointsRequired,
			String loyaltyProgramLevel, Integer multiplier) {
		super();
		this.brandId = brandId;
		this.loyaltyProgramId = loyaltyProgramId;
		this.pointsRequired = pointsRequired;
		this.loyaltyProgramLevel = loyaltyProgramLevel;
		this.multiplier = multiplier;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getLoyaltyProgramId() {
		return loyaltyProgramId;
	}

	public void setLoyaltyProgramId(Integer loyaltyProgramId) {
		this.loyaltyProgramId = loyaltyProgramId;
	}

	public String getPointsRequired() {
		return pointsRequired;
	}

	public void setPointsRequired(String pointsRequired) {
		this.pointsRequired = pointsRequired;
	}

	public String getLoyaltyProgramLevel() {
		return loyaltyProgramLevel;
	}

	public void setLoyaltyProgramLevel(String loyaltyProgramLevel) {
		this.loyaltyProgramLevel = loyaltyProgramLevel;
	}

	public Integer getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(Integer multiplier) {
		this.multiplier = multiplier;
	}

}
