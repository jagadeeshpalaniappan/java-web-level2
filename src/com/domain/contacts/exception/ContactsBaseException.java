package com.domain.contacts.exception;

public class ContactsBaseException extends Exception{
	
	//private static final long serialVersionUID = 1L;	
	private String simpleMessage	= null;

	public ContactsBaseException() {
		super();
	}

	/**
	 * @param message
	 */
	public ContactsBaseException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param tr
	 */
	public ContactsBaseException(String message, Throwable tr) {
		super(message, tr);		
	}

	/**
	 * @param ex
	 */
	public ContactsBaseException(ContactsBaseException ex){
		this.setSimpleMessage(ex.getSimpleMessage());

	}	

	/**
	 * Description of the method	
	 * @return the simpleMessage
	 */
	public String getSimpleMessage() {
		return simpleMessage;
	}

	/**
	 * Description of the method
	 * @param simpleMessage the simpleMessage to set
	 */
	public void setSimpleMessage(String simpleMessage) {
		this.simpleMessage = simpleMessage;
	}

}
