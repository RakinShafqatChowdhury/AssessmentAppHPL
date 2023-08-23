package com.example.assessmentapphpl.model;

import com.google.gson.annotations.SerializedName;

public class TaskDataResponseModel {

	@SerializedName("id")
	private String taskId;
	@SerializedName("title")
	private String taskTitle;

	@SerializedName("details")
	private String taskDetails;

	@SerializedName("contactNumber")
	private String userPhoneNumber;

	@SerializedName("entryDate")
	private String taskEntryDate;

	@SerializedName("purpose")
	private String taskPurpose;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskEntryDate() {
		return taskEntryDate;
	}

	public void setTaskEntryDate(String taskEntryDate) {
		this.taskEntryDate = taskEntryDate;
	}

	public String getTaskPurpose() {
		return taskPurpose;
	}

	public void setTaskPurpose(String taskPurpose) {
		this.taskPurpose = taskPurpose;
	}

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