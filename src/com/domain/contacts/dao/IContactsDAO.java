package com.domain.contacts.dao;

import java.sql.Connection;
import java.util.List;

import com.domain.contacts.exception.ContactsDAOException;
import com.domain.contacts.vo.ContactVO;
import com.domain.contacts.vo.GenericMessageVO;

public interface IContactsDAO {

	
	public List<ContactVO> getAllContacts() throws ContactsDAOException;
	public List<ContactVO> getAllContacts(Connection connection)  throws ContactsDAOException;
	
	public GenericMessageVO saveContact(ContactVO contactVO)  throws ContactsDAOException;
	public GenericMessageVO saveContact(ContactVO contactVO, Connection connection)  throws ContactsDAOException;
	
	
	public ContactVO getContact(ContactVO contactVO) throws ContactsDAOException;
	public ContactVO getContact(ContactVO contactVO, Connection connection)  throws ContactsDAOException;
	public GenericMessageVO deleteContact(ContactVO contactVO)  throws ContactsDAOException;
	public GenericMessageVO deleteContact(ContactVO contactVO, Connection connection)  throws ContactsDAOException;
	
	
}
