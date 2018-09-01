package com.ninjaclub.dashboard.service.common.nao;

import java.util.HashMap;
import java.util.Map;

import com.ninjaclub.dashboard.model.enums.ResponseCode;

/**
 * @author shreya
 *
 */
public class ResultNAO {

	protected ResponseCode code = ResponseCode.ERROR;
	
	protected String message;
	
	protected int data;

	public ResponseCode getCode() {
		return code;
	}

	public void setCode(ResponseCode code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
	
	
}
