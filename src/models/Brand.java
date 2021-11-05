package models;

public class Brand {
	
	public Integer UserId;
	
	public String loyaltyType;
	
	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Integer contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getLoyaltyType() {
		return loyaltyType;
	}
	
	public void setLoyaltyType(String loyaltyType) {
		this.loyaltyType = loyaltyType;
	}

	public String userName;
	
	public String passWord;
	
	public String brandName;
	
	public String address;
	
	public Integer contactNumber;
	
	
}
