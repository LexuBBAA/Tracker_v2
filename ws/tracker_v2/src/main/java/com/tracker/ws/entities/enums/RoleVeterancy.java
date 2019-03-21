package com.tracker.ws.entities.enums;

public enum RoleVeterancy {

	Junior("JUNIOR"), Senior("SENIOR");

	private String value;
	RoleVeterancy(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
