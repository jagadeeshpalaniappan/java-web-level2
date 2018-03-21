package com.domain.contacts.vo;

import java.io.Serializable;


//Y this class is only Serializable?

public class GenericMessageVO extends BaseVO implements Serializable{

	private String code;
	private String message;
	private boolean dbFlag = true;
	private boolean wsFlag = true;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isDbFlag() {
		return dbFlag;
	}
	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}
	public boolean isWsFlag() {
		return wsFlag;
	}
	public void setWsFlag(boolean wsFlag) {
		this.wsFlag = wsFlag;
	}
	
}
