package com.domain.contacts.util;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.domain.contacts.constants.ContactsConstants;
import com.domain.contacts.dao.ContactsDAOImpl;

public class JWebUtil {
	
	private static Logger logger = Logger.getLogger(ContactsDAOImpl.class);
	
	private static ApplicationContext ctx = null;
	
	public static <T> T populate(Class<T> clazz,HttpServletRequest request){
		T object = null;

		try {
			object = clazz.newInstance();
			BeanUtils.populate(object, request.getParameterMap());
		} catch (InstantiationException e) {
			logger.error(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(),e);
		}
		return object;
	}
	
	
	
	
	/**
	 * @param key
	 * @param request
	 * @return String
	 * 
	 *  For Getting the message property values into java classes
	 */
	public static String getMessage(String key, HttpServletRequest request){
		return getMessage(key, null, request);
	}
	
	/**
	 * @param key
	 * @param placeHolders
	 * @param request
	 * @return String
	 * 
	 * For Getting the message property values into java classes
	 */
	public static String getMessage(String key,Object[] placeHolders, HttpServletRequest request){

		ctx = 	WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		MessageSource messageSource = (MessageSource) ctx.getBean(ContactsConstants.MESSAGE_SOURCE);
		return messageSource.getMessage(key, placeHolders, request.getLocale());

	}

}
