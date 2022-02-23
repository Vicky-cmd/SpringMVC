package com.infotrends.in.utils;

public enum UserRole {

	USER("U"), MODERATOR("M"), ADMIN("A");
	
	UserRole(String value) {
		this.value=value;
	} 
	
	private String value;

	public String getValue() {
		return value;
	}
}
