package com.tasktogether.dto.member;

import java.util.List;

import com.tasktogether.dto.MinUserInfo;

public class Membernew {

	private Long projectId;
	private List<MinUserInfo> members;

	public Membernew() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Membernew(Long projectId, List<MinUserInfo> members) {
		super();
		this.projectId = projectId;
		this.members = members;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public List<MinUserInfo> getMembers() {
		return members;
	}

	public void setMembers(List<MinUserInfo> members) {
		this.members = members;
	}

}
