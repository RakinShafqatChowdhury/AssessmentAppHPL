package com.example.assessmentapphpl.model;

import com.google.gson.annotations.SerializedName;

public class TaskEntryModel {

	@SerializedName("title")
	private String taskTitle;

	@SerializedName("details")
	private String taskDetails;

	@SerializedName("contact_number")
	private String userPhoneNumber;


	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDetails() {
		return taskDetails;
	}

	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
}