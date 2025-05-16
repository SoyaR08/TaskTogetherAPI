package com.tasktogether.dto.historical;

import com.tasktogether.model.Historical;

public class HistoricalList {

	private String profile_pic;
	
	private String name;
	
	private String changeDescription;
	
	private String changeDate;

	public HistoricalList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoricalList(String profile_pic, String name, String changeDescription, String changeDate) {
		super();
		this.profile_pic = profile_pic;
		this.name = name;
		this.changeDescription = changeDescription;
		this.changeDate = changeDate;
	}
	
	public HistoricalList(Historical h) {
		super();
		this.profile_pic = h.getUser().getProfile_pic();
		this.name = h.getUser().getName();
		this.changeDescription = h.getChangeDescription();
		this.changeDate = h.getChangeDate().toString();
	}

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChangeDescription() {
		return changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	
	
}
