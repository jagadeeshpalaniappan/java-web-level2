package com.domain.contacts.vo;

import java.util.List;

public class ContactVO {
	
	private Long contactId;
	private String contactName;
	private String contactMobile;
	private String contactLandline;
	
	
	private List<LocationVO> locationVOs = null;


	public Long getContactId() {
		return contactId;
	}


	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	public String getContactMobile() {
		return contactMobile;
	}


	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}


	public String getContactLandline() {
		return contactLandline;
	}


	public void setContactLandline(String contactLandline) {
		this.contactLandline = contactLandline;
	}


	public List<LocationVO> getLocationVOs() {
		return locationVOs;
	}


	public void setLocationVOs(List<LocationVO> locationVOs) {
		this.locationVOs = locationVOs;
	}

	
	

}
