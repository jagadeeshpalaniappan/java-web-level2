package com.domain.contacts.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;



public class JGenericUtil {


	private static Logger logger = Logger.getLogger(JGenericUtil.class);
	


	
	/********************************** isNotNull***********************************/
	/**
	 * @Description To check the given string value for null
	 * @param	String - attribute	
	 * @return	boolean	 
	 */
	public static boolean isNotNull(String attribute) {

		boolean check = false;

		if ((attribute != null)&& !("".equalsIgnoreCase(attribute.trim()))) {

			check = true;
		}
		return check;
	}

	/**
	 * @Description To check the given Long value for null
	 * @param	Long - attribute	
	 * @return	boolean	 
	 */
	public static boolean isNotNull(Long attribute) {

		boolean check = false;

		if (attribute != null && (attribute.longValue() != 0)) {

			check = true;
		}
		return check;
	}


	public static boolean isNotNull(Integer attribute) {

		boolean check = false;

		if (attribute != null && (attribute.intValue() != 0)) {

			check = true;
		}
		return check;
	}

	/**
	 * @Description To check the given Array value for null
	 * @param	Array - attribute	
	 * @return	boolean	 
	 */
	public static boolean isNotNull(String[] attribute) {

		boolean check = false;

		if (attribute != null && (attribute.length > 0)) {

			check = true;
		}
		return check;
	}

	/**
	 * @Description To check the given List value for null
	 * @param	Array - attribute	
	 * @return	boolean	 
	 */
	public static boolean isNotNull(List attribute){
		boolean check = false;
		if (attribute != null && (attribute.size() > 0)){
			check = true;
		}
		return check;
	}		

	
	/**
	 * @Description To check the given Object is not null
	 * @param	String - attribute	
	 * @return	boolean	 
	 */
	public static boolean isNotNull(Object attribute) {

		boolean check = false;

		if ((attribute != null)) {

			check = true;
		}
		return check;
	}
	
	
	/********************************** /isNotNull***********************************/
	
	
	
	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 *  Date [dd-MMM-yyyy] to String [MM/dd/yyyy]
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the UI
	 */
	public static final String convertDateToString(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		String date_Pattern= "MM/dd/yyyy";

		if (aDate != null) {
			df = new SimpleDateFormat(date_Pattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
	
}