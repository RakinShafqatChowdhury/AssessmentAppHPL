package com.example.assessmentapphpl.model;

import com.google.gson.annotations.SerializedName;

public class UserRegistrationModel {

	@SerializedName("name")
	private String userName;

	@SerializedName("email")
	private String userEmail;

	@SerializedName("contactNumber")
	private String userPhoneNumber;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
}