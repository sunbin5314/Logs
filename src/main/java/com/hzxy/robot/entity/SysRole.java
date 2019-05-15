package com.hzxy.robot.entity;

import java.io.Serializable;

public class SysRole implements Serializable {

	private String id;
	private String role;
	private String description;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SysRole{" +
				"id='" + id + '\'' +
				", role='" + role + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
