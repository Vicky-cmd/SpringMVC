package com.infotrends.in.utils;


public enum UserStatus {

	ACTIVE("A"), LOCKED("L"), DELETED("D"), EXPIRED("E");
	
	UserStatus(String value) {
		this.value=value;
	} 
	
	private String value;

	public String getValue() {
		return value;
	}

}
