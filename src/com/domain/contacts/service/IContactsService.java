package com.domain.contacts.service;

import java.util.List;

import com.domain.contacts.exception.ContactsServiceException;
import com.domain.contacts.vo.ContactVO;
import com.domain.contacts.vo.GenericMessageVO;

public interface IContactsService {
	
	public List<ContactVO> getAllContacts() throws ContactsServiceException;
	
	public GenericMessageVO saveContact(ContactVO contactVO) throws ContactsServiceException;
	public ContactVO getContact(ContactVO contactVO) throws ContactsServiceException;
	public GenericMessageVO deleteContact(ContactVO contactVO) throws ContactsServiceException;
		

}
