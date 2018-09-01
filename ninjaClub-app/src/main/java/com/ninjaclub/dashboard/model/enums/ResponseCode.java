package com.ninjaclub.dashboard.model.enums;

/**
 * @author shreya
 *
 */
public enum ResponseCode {

	ERROR(0),
	SUCCESS(1);
	
	private int code;

	private ResponseCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
