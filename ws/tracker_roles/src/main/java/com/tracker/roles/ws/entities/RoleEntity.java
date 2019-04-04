package com.tracker.roles.ws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tracker.roles.ws.entities.enums.RoleVeterancy;

@Entity(name = "roles")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 3890582832322826993L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title", nullable = false, length = 100, unique = true)
	private String title;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "department_id", nullable = false)
	private int departmentId;
	
	@Column(name = "veterancy", nullable = true)
	@Enumerated(EnumType.STRING)
	private RoleVeterancy veterancy;
	
	public RoleEntity() {}
	
	public RoleEntity(String title, String description, int departmentId, RoleVeterancy veterancy, int accessProfileId) {
		this.title = title;
		this.description = description;
		this.departmentId = departmentId;
		this.veterancy = veterancy;
	}

	public int getId() { return id; }

	public String getTitle() { return title; }

	public String getDescription() { return description; }

	public int getDepartmentId() { return departmentId; }

	public RoleVeterancy getVeterancy() { return veterancy; }

}
