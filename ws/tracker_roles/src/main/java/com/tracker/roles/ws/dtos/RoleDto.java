package com.tracker.roles.ws.dtos;

import java.io.Serializable;

import com.tracker.roles.ws.entities.enums.RoleVeterancy;

public class RoleDto implements Serializable {

	private static final long serialVersionUID = 7469346989705681368L;

	private int id;
	
	private String title;
	
	private String description;
	
	private RoleVeterancy veterancy;
	
	private int departmentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoleVeterancy getVeterancy() {
		return veterancy;
	}

	public void setVeterancy(RoleVeterancy veterancy) {
		this.veterancy = veterancy;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
}
