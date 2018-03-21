package com.domain.contacts.exception;

public class ContactsDAOException extends ContactsBaseException{

	//private static final long serialVersionUID = 1L;

	public ContactsDAOException() {
	}

	/**
	 * @param message
	 */
	public ContactsDAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param tr
	 */
	public ContactsDAOException(String message, Throwable tr) {
		super(message, tr);
	}

	/**
	 * @param ex
	 */
	public ContactsDAOException(ContactsBaseException ex) {
		super(ex);
	}

	
}
