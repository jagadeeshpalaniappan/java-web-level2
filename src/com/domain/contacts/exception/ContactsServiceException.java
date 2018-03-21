package com.domain.contacts.exception;

public class ContactsServiceException extends ContactsBaseException {

		private static final long serialVersionUID = 1L;
		public ContactsServiceException() {
		}

		/**
		 * @param message
		 */
		public ContactsServiceException(String message) {
			super(message);
		}

		/**
		 * @param message
		 * @param tr
		 */
		public ContactsServiceException(String message, Throwable tr) {
			super(message, tr);
		}

		/**
		 * @param dex
		 */
		public ContactsServiceException(ContactsDAOException dex){
			super(dex.getSimpleMessage());
		}

		/**
		 * @param dex
		 */
		public ContactsServiceException(ContactsBaseException dex){
			super(dex.getSimpleMessage());
		}


	}

