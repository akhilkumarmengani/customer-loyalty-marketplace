package models;
import java.sql.Date;

public class Brand {
	Integer brandId;
	String name;
	String address;
	Date joinDate;
	
	public Integer getBrandId() {
		return brandId;
	}



	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Date getJoinDate() {
		return joinDate;
	}



	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}



	@Override
	public String toString() {
		return "Brand Name : " + this.name + " Address: " + this.address + " Join Date : " + this.joinDate;
	}
}
