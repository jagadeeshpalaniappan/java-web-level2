package com.domain.contacts.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.domain.contacts.dao.ContactsDAOImpl;
import com.domain.contacts.dao.IContactsDAO;
import com.domain.contacts.exception.ContactsDAOException;
import com.domain.contacts.exception.ContactsServiceException;
import com.domain.contacts.vo.ContactVO;
import com.domain.contacts.vo.GenericMessageVO;

public class ContactsServiceImpl implements IContactsService {

	private static Logger logger = Logger.getLogger(ContactsServiceImpl.class);
	
	private IContactsDAO contactsDAO = null;
	public void setContactsDAO(IContactsDAO contactsDAO) {
		this.contactsDAO = contactsDAO;
	}

	
	
	@Override
	public List<ContactVO> getAllContacts() throws ContactsServiceException{
		List<ContactVO> contactVOs = null;
		
		try {
			contactVOs = contactsDAO.getAllContacts();
		} catch (ContactsDAOException e) {
			logger.error(e.getMessage(),e);
			throw new ContactsServiceException(e.getMessage(),e);
		}
		
		return contactVOs;
	}


	@Override
	public ContactVO getContact(ContactVO contactVO) throws ContactsServiceException {

		try {
			contactVO = contactsDAO.getContact(contactVO);
		} catch (ContactsDAOException e) {
			logger.error(e.getMessage(),e);
			throw new ContactsServiceException(e.getMessage(),e);
		}
		
		return contactVO;
	}




	@Override
	public GenericMessageVO saveContact(ContactVO contactVO) throws ContactsServiceException {
		GenericMessageVO genericMessageVO = null;
		try {
			genericMessageVO = contactsDAO.saveContact(contactVO);
		} catch (ContactsDAOException e) {
			logger.error(e.getMessage(),e);
			throw new ContactsServiceException(e.getMessage(),e);
		}
		
		return genericMessageVO;
	}




	@Override
	public GenericMessageVO deleteContact(ContactVO contactVO) throws ContactsServiceException {
		GenericMessageVO genericMessageVO = null;
		try {
			genericMessageVO = contactsDAO.deleteContact(contactVO);
		} catch (ContactsDAOException e) {
			logger.error(e.getMessage(),e);
			throw new ContactsServiceException(e.getMessage(),e);
		}
		
		return genericMessageVO;
	}
	
	


}
