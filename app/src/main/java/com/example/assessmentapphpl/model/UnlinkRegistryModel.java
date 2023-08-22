package com.example.assessmentapphpl.model;

import com.google.gson.annotations.SerializedName;

public class UnlinkRegistryModel {
	@SerializedName("contactNumber")
	private String userPhoneNumber;

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
}